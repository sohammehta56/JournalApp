package com.learn.myFirstProject.controller;

import com.learn.myFirstProject.entity.JournalEntry;
import com.learn.myFirstProject.entity.User;
import com.learn.myFirstProject.service.JournalService;
import com.learn.myFirstProject.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody final JournalEntry journalEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntry.setDate(LocalDateTime.now());
            journalService.saveJournalEntry(journalEntry, username);
            return new ResponseEntity(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesforUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        final User user = userService.getUserByUsername(username);
        return new ResponseEntity(user.getJournalEntries(), HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable final ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        List<JournalEntry> usersJournal =
                user.getJournalEntries().stream().filter(journalEntry -> journalEntry.getId().equals(id)).collect(
                        Collectors.toList());
        if (!usersJournal.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalService.getJournalEntryById(id);
            if (journalEntry.isPresent()) {
                return new ResponseEntity(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable final ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Boolean isRemoved = journalService.deleteJournalEntryById(id, username);
        if (isRemoved) {
            return new ResponseEntity<>("Journal Deleted Successfully", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable final ObjectId id,
                                                               @RequestBody final JournalEntry journalEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        List<JournalEntry> usersJournal =
                user.getJournalEntries().stream().filter(entry -> entry.getId().equals(id)).collect(
                        Collectors.toList());
        if(!usersJournal.isEmpty()){
            final JournalEntry existingJournalEntry = journalService.getJournalEntryById(id).orElse(null);
            if (Objects.nonNull(existingJournalEntry)) {
                existingJournalEntry.setTitle(
                        journalEntry.getTitle() != null && !journalEntry.getTitle().equalsIgnoreCase("")
                        ? journalEntry.getTitle() : existingJournalEntry.getTitle());
                existingJournalEntry.setContent(
                        journalEntry.getContent() != null && !journalEntry.getContent().equalsIgnoreCase("")
                        ? journalEntry.getContent() : existingJournalEntry.getContent());
                journalService.updateJournalEntry(existingJournalEntry);
                return new ResponseEntity<>(existingJournalEntry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
