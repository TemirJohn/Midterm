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
                         department_id BIGINT REFERENCES department(id) ON DELETE CASCADE
);

CREATE TABLE student_teachers (
                                  student_id BIGINT NOT NULL REFERENCES student(id) ON DELETE CASCADE,
                                  teachers_id BIGINT NOT NULL REFERENCES teacher(id) ON DELETE CASCADE
);

CREATE INDEX idx_student_department ON student(department_id);
CREATE INDEX idx_student_teachers_student ON student_teachers(student_id);
CREATE INDEX idx_student_teachers_teacher ON student_teachers(teachers_id);