CREATE TABLE country
(
    id           bigserial primary key,
    code         varchar(255) not null,
    country_name varchar(255) not null
);

create index index_country_name_id_on_country
    on country(country_name);

CREATE TABLE region
(
    id          bigserial primary key,
    title       varchar(255) not null,
    description text
);

CREATE TABLE city
(
    id         bigserial primary key,
    city_name  varchar(255)              not null,
    region_id  bigint references region  not null,
    country_id bigint references country not null
);

create index index_country_id_on_city
    on city(country_id);

create index index_city_name_on_city
    on city(city_name);

CREATE TABLE location
(
    id        bigserial primary key,
    city_id   bigint references city        not null,
    street    varchar(255),
    lat       numeric(14, 6) not null,
    lng       numeric(14, 6) not null,
    source    varchar(255) not null
);

CREATE TABLE traveler
(
    id           bigserial primary key,
    first_name   varchar(255)               not null,
    last_name    varchar(255)               not null,
    middle_name  varchar(255)               not null,
    phone_number varchar(255),
    location_id  bigint references location not null,
    is_deleted   boolean default false      not null,
    created_at   timestamp with time zone,
    changed_at   timestamp with time zone
);


CREATE TABLE traveler_geo_position_history (
    id          bigserial primary key,
    traveler_id bigint references traveler not null,
    lat         numeric(14, 6)             not null,
    lng         numeric(14, 6)             not null,
    status      varchar(255)               not null,
    created_at  timestamp with time zone,
    changed_at  timestamp with time zone
);

CREATE TABLE traveler_preferences (
    id          bigserial primary key,
    traveler_id bigint references traveler not null,
    title       varchar(255)               not null,
    description varchar(255)
);

CREATE TABLE tour_agency
(
    id          bigserial primary key,
    agency_name varchar(255)               not null,
    location_id bigint references location not null,
    is_deleted  boolean default false      not null,
    created_at  timestamp with time zone,
    changed_at  timestamp with time zone
);


CREATE TABLE tour_operator (
    id          bigserial primary key,
    agency_id   bigint references tour_agency not null,
    first_name  varchar(255)                  not null,
    last_name   varchar(255)                  not null,
    middle_name varchar(255)                  not null,
    is_deleted  boolean default false         not null,
    created_at  timestamp with time zone,
    changed_at  timestamp with time zone
);

CREATE TABLE tour (
    id           bigserial primary key,
    traveler_id  bigint references traveler    not null,
    agency_id    bigint references tour_agency not null,
    location_id  bigint references location    not null,
    tour_name    text                          not null,
    price        numeric(14, 2)                not null,
    is_deleted   boolean default false         not null,
    description  text,
    start_date   date                          not null,
    end_date     date                          not null,
    created_at   timestamp with time zone,
    changed_at   timestamp with time zone
);

CREATE TABLE airport (
    id           bigserial primary key,
    airport_name varchar(255)               not null,
    location_id  bigint references location not null,
    is_deleted  boolean default false not null,
    created_at   timestamp with time zone,
    changed_at   timestamp with time zone
);

CREATE TABLE registration_airport
(
    id            bigserial primary key,
    traveler_id   bigint references traveler not null,
    airport_id    bigint references airport  not null,
    arrived_date  date                       not null,
    departed_date date                       not null,
    status        varchar(255) default 'ARRIVED':: character varying not null,
    is_deleted    boolean      default false not null,
    created_at    timestamp with time zone   not null,
    changed_at    timestamp with time zone
);

-- ARRIVED;
-- DEPARTED;
CREATE TABLE journal_visit
(
    id          bigserial primary key,
    traveler_id bigint references traveler      not null,
    operator_id bigint references tour_operator not null,
    tour_id     bigint references tour          not null,
    visit_date  date                            not null,
    is_arrived  boolean default false           not null,
    is_deleted  boolean default false           not null,
    created_at  timestamp with time zone        not null,
    changed_at  timestamp with time zone
);