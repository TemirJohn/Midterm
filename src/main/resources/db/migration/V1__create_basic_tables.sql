CREATE TABLE department (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE teacher (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE student (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    department_id BIGINT,
    CONSTRAINT fk_student_department FOREIGN KEY (department_id)
    REFERENCES department(id) ON DELETE CASCADE
);

CREATE TABLE student_teachers (
  student_id BIGINT NOT NULL,
  teachers_id BIGINT NOT NULL,
  CONSTRAINT fk_student_teachers_student FOREIGN KEY (student_id)
      REFERENCES student(id) ON DELETE CASCADE,
  CONSTRAINT fk_student_teachers_teacher FOREIGN KEY (teachers_id)
      REFERENCES teacher(id) ON DELETE CASCADE
);

