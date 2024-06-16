package com.learn.myFirstProject.service;

import com.learn.myFirstProject.entity.JournalEntry;
import com.learn.myFirstProject.entity.User;
import com.learn.myFirstProject.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired UserService userService;

    @Transactional
    public void saveJournalEntry(final JournalEntry journalEntry, final String username) {
        final User user = userService.getUserByUsername(username);
        JournalEntry savedJounalEntry =  journalRepository.save(journalEntry);
        user.getJournalEntries().add(savedJounalEntry);
        userService.saveUser(user);
    }

    public void updateJournalEntry(final JournalEntry journalEntry) {
        journalRepository.save(journalEntry);
    }

    public List<JournalEntry> getJournalEntries() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(final ObjectId id) {
        return journalRepository.findById(id);
    }

    @Transactional
    public Boolean deleteJournalEntryById(final ObjectId id, final String username) {
        Boolean isRemoved = Boolean.FALSE;
        try {
            final User user = userService.getUserByUsername(username);
            isRemoved = user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
            if (isRemoved) {
                userService.saveUser(user);
                journalRepository.deleteById(id);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Journal Entry not found with id: " + id + " error: " + e.getMessage());
        }
        return isRemoved;
    }
}
