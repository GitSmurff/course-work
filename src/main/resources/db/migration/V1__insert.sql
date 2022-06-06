insert into country(id, country_name, code)
VALUES (1, 'Afghanistan', 'AF/AFG'),
       (2, 'Australia', 'AU/AUS'),
       (3, 'Bhutan', 'BT/BTN'),
       (4, 'Belarus', 'BY/BLR'),
       (5, 'Canada', 'CA/CAN'),
       (6, 'China', 'CN/CHN'),
       (7, 'Egypt', 'EG/EGY'),
       (8, 'France', 'FR/FRA'),
       (9, 'Japan', 'JP/JPN'),
       (10, 'Kyrgyzstan', 'KG/KGZ');


insert into region(id, title, description)
VALUES (1, 'Деловой', 'Связан с индустрией деловых мероприятий и встреч'),
       (2, 'Рекреационный', 'Отдых на пляжах, купание в море, беззаботное времяпровождение, когда можно ничего не делать, а просто наслаждаться отпууском'),
       (3, 'Религиозный', 'Посещение храмов, соборов, монастырей и прочих святынь'),
       (4, 'Научный', null),
       (5, 'Активный', 'Занятие спортом, рыбалка, охота, посящение аттракционов'),
       (6, 'Культурно-позновательный', 'Осмотр достопримечательностей, памятников истории'),
       (7, 'Оздоровительный', 'Связан с отдыхом в санаториях, на курортах с лечебной водой, грязями, чистымм воздухом');


insert into city(id, country_id, city_name, region_id)
values (1, 1,'JALALABAD', 6),
       (2, 1,'KABUL', 3),
       (3, 1,'KANDAHAR', 3),
       (4, 2,'CASEY', 5),
       (5, 2,'DAVIS', 2),
       (6, 2,'MAWSON', 1),
       (7, 3,'BASOCHU', 2),
       (8, 3,'CHAPCHA', 2),
       (9, 3,'JAKAR', 5),
       (10, 4,'IVANOVO', 1),
       (11, 4,'PINSK', 2),
       (12, 4,'STOLIN', 6),
       (13, 5,'ALBERTA', 5),
       (14, 5,'OTTAWA', 7),
       (15, 5,'SASKATOON', 5),
       (16, 6,'AKESU-XINJIANG', 4),
       (17, 6,'CHANGYE-SHANXI', 1),
       (18, 6,'FENGHUA - ZHEJIANG', 3),
       (19, 7,'ALEXANDRIA', 6),
       (20, 7,'DAKAHLIA', 3),
       (21, 7,'LUXOR', 7),
       (22, 8,'NORTHEAST FRANCE', 2),
       (23, 8,'PARIS AND PARIS REGION', 1),
       (24, 8,'SOUTHWEST FRANCE', 3),
       (25, 9,'OSAKA', 6),
       (26, 9,'TOKYO', 4),
       (27, 9,'YOKOHAMA', 1),
       (28, 10,'BISHKEK', 6),
       (29, 10,'OSH', 3),
       (30, 10,'ISSYK-KUL', 7);

-- AIRPORT
-- TRAVELER
-- AGENCY
-- TOUR
insert into location (id, city_id, street, lat, lng, source)
VALUES (1, 1, 'CF3X+7FV', 34.419849,70.472943, 'AIRPORT'),
       (2, 2, 'H666+H2F', 34.5614444,69.2100623, 'AIRPORT'),
       (3, 3, 'GV22+W79', 31.5022998,65.8507244, 'AIRPORT'),
       (4, 4, '101 Airport Road', 39.3045503,-88.0022328, 'AIRPORT'),
       (5, 1, 'WXGW+V2', 41.9299486,72.1986418, 'AGENCY'),
       (6, 2, 'G5PQ+Q26', 38.8009917,65.9161298, 'AGENCY'),
       (7, 3, 'JPC3+WR6', 42.8699284,74.6049057, 'AGENCY'),
       (8, 1, 'W2V5+WR7', 40.931975, 73.000084, 'TRAVELER'),
       (9, 1, '2501 23rd Ave S, Fargo', 4.3884522,-163.9574162, 'TOUR');

insert into airport (id, airport_name, location_id)
values (1, 'Jalal Abad Airport', 1),
       (2, 'Hamid Karzai International Airport', 2),
       (3, 'Ahmad Shah Baba International Airport', 3),
       (4, 'Casey Municipal Airport', 4);

insert into traveler (id, first_name, last_name, middle_name, phone_number, location_id)
VALUES (1, 'Никита', 'Инчин', 'Сергеевич', +996555312312, 8);

insert into registration_airport (id, traveler_id, airport_id, arrived_date, departed_date, created_at)
values (1, 1, 1, '2022-01-01', '2022-01-13', '2022-01-01 13:56:39.324168 +00:00');

insert into tour_agency (id, agency_name, location_id)
VALUES (1, 'Jazzira trevel', 5),
       (2, 'Sky Travel & Tours', 6),
       (3, 'Mehri Tourist And Travel', 7);

insert into tour_operator (id, agency_id, first_name, last_name, middle_name)
VALUES (1, 1, 'Иван', 'Попов', 'Иванович'),
       (2, 2, 'Николай', 'Петров', 'Сергеевич'),
       (3, 3, 'Петр', 'Иваников', 'Александрович');

-- insert into tour_type (agency_id, title)
-- VALUES (1, 'Экскурсии'),
--        (1, 'Береговые экскурсии'),
--        (1, 'Приключенческие или спортивные'),
--        (1, 'Кулинарные, гастрономические и винные туры'),
--        (1, 'Культурные и тематические'),
--        (1, 'Семейные'),
--        (1, 'Праздничные и сезонные'),
--        (2, 'Экскурсии'),
--        (2, 'Роскошные'),
--        (2, 'Велотуры'),
--        (2, 'Культурные и тематические');

insert into tour (id, traveler_id, agency_id, location_id, tour_name, price, description, start_date,
                  end_date)
values (1, 1, 1, 9, 'Спортивный тур', 1400, null, '2022-01-01', '2022-01-13');

insert into traveler_preferences (id, traveler_id, title, description)
values (1, 1, 'Охота', null);

insert into journal_visit (id, traveler_id, operator_id, tour_id, visit_date, is_arrived, created_at)
VALUES (1, 1, 1, 1, '2022-01-02', true, '2022-01-02 13:56:39.324168 +00:00');

insert into traveler_geo_position_history (id, traveler_id, lat, lng, status, created_at)
VALUES (1, 1, 40.931975, 73.000084, 'OK', '2021-12-31 11:35:01.124168 +00:00'),
       (2, 1, 39.981975, 72.975684, 'OK', '2022-01-01 08:15:00.224168 +00:00'),
       (3, 1, 36.031975, 71.020084, 'OK', '2022-01-01 11:56:39.324168 +00:00'),
       (4, 1, 34.419848, 70.502943, 'OK', '2022-01-01 13:00:40.424168 +00:00'),
       (5, 1, 20.419848, -96.502943, 'OK', '2022-01-01 20:08:51.524168 +00:00'),
       (6, 1, 4.3884522,-163.9574162, 'OK', '2022-01-02 03:30:00.225168 +00:00'),
       (7, 1, 6.354512,-154.7574364, 'OK', '2022-01-02 13:00:40.424168 +00:00');


