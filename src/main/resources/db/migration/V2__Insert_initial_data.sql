INSERT INTO department (name) VALUES
                                  ('Computer Science'),
                                  ('Mathematics'),
                                  ('Physics');

INSERT INTO teacher (name) VALUES
                               ('Dr. Smith'),
                               ('Prof. Johnson'),
                               ('Dr. Williams');

INSERT INTO student (name, department_id) VALUES
                                              ('Alice Anderson', 1),
                                              ('Bob Brown', 1),
                                              ('Carol Carter', 2);

INSERT INTO student_teachers (student_id, teachers_id) VALUES
                                                           (1, 1),
                                                           (1, 2),
                                                           (2, 1),
                                                           (2, 3),
                                                           (3, 2);