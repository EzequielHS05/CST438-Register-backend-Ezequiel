## CST438 Software Engineering California State University Monterey Bay
## Registration service project
### Users can view their course schedule and add/drop courses 

### REST apis  used by front end 

#### GET /schedule?year={year}&semester={semester}
- query parameters - year such as 2021,   semester such as Spring, Fall 
- result returned JSON for instance of java class ScheduleDTO.    

#### POST /schedule 
- body contains JSON data for CourseDTO.  See java class ScheduleDTO.CourseDTO

#### DELETE /schedule/{enrollment_id}  
- enrollment_id from a course enrollment  See ScheduleDTO.CourseDTO.id 

### Database Tables
- Course - course number, title, year, semester, hours, location, instructor, start and end dates
- Student - id, name and email of student
- Enrollment - student id, course number, year, semester

### Rest apis used by other services
- tbd 

