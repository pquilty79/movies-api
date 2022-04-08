
CREATE TABLE movies (
                        id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        director VARCHAR(50) NOT NULL,
                        actors VARCHAR(350) NOT NULL,
                        genre VARCHAR(100) NOT NULL,
                        year INT NOT NULL,
                        rating DOUBLE NOT NULL ,
                        plot VARCHAR(3000) NOT NULL,
                        poster VARCHAR(250) NOT NULL,
                        trailerURL VARCHAR(250) NOT NULL,
                        runtime VARCHAR(25) NOT NULL,
                        mpaa VARCHAR(10) NOT NULL ,
                        favorite BOOLEAN DEFAULT false
);

CREATE TABLE actors (
    actor_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id INT UNSIGNED,
    FOREIGN KEY (id) REFERENCES movies(id),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50)
);

CREATE TABLE directors (
                        dir_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        id INT UNSIGNED,
                        FOREIGN KEY (id) REFERENCES movies(id),
                        first_name VARCHAR(50) NOT NULL,
                        last_name VARCHAR(50)
);
