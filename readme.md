
- tao thu muc data 
- install file jar h2 tai ve
```javascript
java -cp h2-1.4.190.jar org.h2.tools.Server
```

luc add them dependency securiy, 
user mac dinh la 'user'
pass mac dinh se dc sinh ra trong log, 'Using default security password: ...'

co the cau hinh user, pass trong file applicaton.properties
```text
spring.security.user.name=user
spring.security.user.password=password
```