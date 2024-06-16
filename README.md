# JournalApp

A Journal App in which authenticated users can add/update/delete journals. Here only admin can add new admin and can see all the users data. This project is implemented using java springboot.

Journal APIs:

1. Get All Journal for user:

curl --location 'localhost:8080/journal' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=C36ABFD22E89B511BCFDF5E39892A572' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTb2hhbSIsImlhdCI6MTcxODEyODgyNCwiZXhwIjoxNzE4MTMyNDI0fQ.K9BQKDWmNXLb1ulh0t6f4zSLHnpuzBlfJVmUpNne6xY'

2. Get Specific Journal for user by ID

curl --location 'localhost:8080/journal/id/665715a504aa205c9f7a5cdb' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=C36ABFD22E89B511BCFDF5E39892A572' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTb2hhbSIsImlhdCI6MTcxODEyODgyNCwiZXhwIjoxNzE4MTMyNDI0fQ.K9BQKDWmNXLb1ulh0t6f4zSLHnpuzBlfJVmUpNne6xY'

3. Update journal for user

curl --location --request PUT 'localhost:8080/journal/id/665716df04aa205c9f7a5cde' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTaHJhZGRoYSIsImlhdCI6MTcxODEyOTUwMywiZXhwIjoxNzE4MTMzMTAzfQ.zdkXPnBvIkeKL-4fjIv5vwA8NOgH1bPsQi--02J3A6U' \
--data '{
"title": "Journal updated",
"content": "Temp content updated"
}'

4. Delete Journal by ID for user

curl --location --request DELETE 'localhost:8080/journal/id/665715a904aa205c9f7a5cdc' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTb2hhbSIsImlhdCI6MTcxODEyODgyNCwiZXhwIjoxNzE4MTMyNDI0fQ.K9BQKDWmNXLb1ulh0t6f4zSLHnpuzBlfJVmUpNne6xY'

5. Create journal for user

curl --location 'localhost:8080/journal' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTb2hhbSIsImlhdCI6MTcxODEyODgyNCwiZXhwIjoxNzE4MTMyNDI0fQ.K9BQKDWmNXLb1ulh0t6f4zSLHnpuzBlfJVmUpNne6xY' \
--data '{
"title": "Journal 1",
"content": "Temp content 1"
}'

User APIs:

1. Update User info

curl --location --request PUT 'localhost:8080/user' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=C36ABFD22E89B511BCFDF5E39892A572' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTb2hhbSIsImlhdCI6MTcxODEyODgyNCwiZXhwIjoxNzE4MTMyNDI0fQ.K9BQKDWmNXLb1ulh0t6f4zSLHnpuzBlfJVmUpNne6xY' \
--data '{
"username": "Soham",
"password": "Soham"
}'

2. Delete User

curl --location --request DELETE 'localhost:8080/user' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTb2hhbSIsImlhdCI6MTcxODEyODgyNCwiZXhwIjoxNzE4MTMyNDI0fQ.K9BQKDWmNXLb1ulh0t6f4zSLHnpuzBlfJVmUpNne6xY' \
--header 'Cookie: JSESSIONID=C36ABFD22E89B511BCFDF5E39892A572' \
--data '{
"username": "Soham",
"password": "Soham"
}'

Public APIs:

1. Create User

curl --location 'localhost:8080/public/signup' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=C36ABFD22E89B511BCFDF5E39892A572' \
--data '{
"username": "testUser",
"password": "testUser"
}'

2. Login

curl --location 'localhost:8080/public/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=C36ABFD22E89B511BCFDF5E39892A572; JSESSIONID=5055ADB3131E5D0058A0F700DCBC8D83' \
--data '{
"username": "Soham",
"password": "Soham"
}'

3. health check

curl --location 'localhost:8080/public/health-check'

Admin APIs:

1. Get All Users

curl --location 'localhost:8080/admin/all-users' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTaHJhZGRoYSIsImlhdCI6MTcxODEyOTUwMywiZXhwIjoxNzE4MTMzMTAzfQ.zdkXPnBvIkeKL-4fjIv5vwA8NOgH1bPsQi--02J3A6U'

2. Create Admin User

curl --location 'localhost:8080/admin/create-admin-user' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTb2hhbSIsImlhdCI6MTcxODEyODgyNCwiZXhwIjoxNzE4MTMyNDI0fQ.K9BQKDWmNXLb1ulh0t6f4zSLHnpuzBlfJVmUpNne6xY' \
--header 'Content-Type: application/json' \
--data '{
"username": "Soham",
"password": "Soham"
}'
