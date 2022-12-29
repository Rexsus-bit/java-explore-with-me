DROP TABLE IF EXISTS users, categories, compilations, events, locations, participation_requests,
    compilations_events_matches, comments;

CREATE TABLE users
(
    id    bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name  varchar(50),
    email varchar(50)
);

CREATE TABLE categories
(
    id   bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar(120)
);

CREATE TABLE compilations
(
    compilation_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    pinned         boolean,
    title          varchar
);

CREATE TABLE events
(
    annotation         varchar,
    category_id        bigint REFERENCES categories (id) ON DELETE NO ACTION,
    confirmed_requests bigint,
    created_on         timestamp,
    description        varchar,
    event_date         timestamp,
    event_id           bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    initiator_id       bigint,
    location_id        bigint,
    paid               boolean,
    participant_limit  int DEFAULT 0 NOT NULL,
    published_on       timestamp,
    request_moderation boolean,
    state              varchar,
    title              varchar,
    views              bigint
);

CREATE TABLE locations
(
    id  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    lat numeric,
    lon numeric
);

CREATE TABLE participation_requests
(
    created      timestamp,
    event_id     bigint,
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    requester_id bigint,
    status       varchar
);

CREATE TABLE compilations_events_matches
(
    compilation_id bigint,
    event_id       bigint,
    PRIMARY KEY (compilation_id, event_id)
);

CREATE TABLE comments
(
    comment_id   bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    comment      varchar,
    owner_id     bigint REFERENCES users (id) ON DELETE CASCADE,
    event_id     bigint REFERENCES events (event_id)  ON DELETE CASCADE,
    comment_date timestamp
);

ALTER TABLE events
    ADD FOREIGN KEY (initiator_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE events
    ADD FOREIGN KEY (location_id) REFERENCES locations (id) ON DELETE RESTRICT;

ALTER TABLE participation_requests
    ADD FOREIGN KEY (event_id) REFERENCES events (event_id)  ON DELETE CASCADE;

ALTER TABLE participation_requests
    ADD FOREIGN KEY (requester_id) REFERENCES users (id)  ON DELETE CASCADE ;

ALTER TABLE compilations_events_matches
    ADD FOREIGN KEY (compilation_id) REFERENCES compilations (compilation_id) ON DELETE CASCADE ;

ALTER TABLE compilations_events_matches
    ADD FOREIGN KEY (event_id) REFERENCES events (event_id) ON DELETE CASCADE ;
