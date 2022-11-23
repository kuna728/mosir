-- activity types
insert into ACTIVITY_TYPE values (1, 'Marzyłeś o pływaniu w świetnie wyposażonym obiekcie i wspaniałej atmosferze? Zapraszamy do mosiru!',
                                     'Pływanie', 'plywanie');
insert into ACTIVITY_TYPE values (2, 'Marzyłeś o jodze w świetnie wyposażonym obiekcie i wspaniałej atmosferze? Zapraszamy do mosiru!',
                                      'Joga', 'joga');
insert into ACTIVITY_TYPE values (3, 'Marzyłeś o tenisie stołowym w świetnie wyposażonym obiekcie i wspaniałej atmosferze? Zapraszamy do mosiru!',
                                      'Tenis stołowy', 'tenis-stolowy');
insert into ACTIVITY_TYPE values (4, 'Marzyłeś o lekkoatletyce w świetnie wyposażonym obiekcie i wspaniałej atmosferze? Zapraszamy do mosiru!',
                                      'Lekkoatletyka', 'lekkoatletyka');
insert into ACTIVITY_TYPE values (5, 'Marzyłeś o siłowni w świetnie wyposażonym obiekcie i wspaniałej atmosferze? Zapraszamy do mosiru!',
                                     'Siłownia', 'silownia');
insert into ACTIVITY_TYPE values (6, 'Marzyłeś o szermierce w świetnie wyposażonym obiekcie i wspaniałej atmosferze? Zapraszamy do mosiru!',
                                     'Szermierka', 'szermierka');
insert into ACTIVITY_TYPE values (7,'Marzyłeś o tenisiew świetnie wyposażonym obiekcie i wspaniałej atmosferze? Zapraszamy do mosiru!',
                                    'Tenis', 'tenis');
insert into ACTIVITY_TYPE values (8, 'Marzyłeś o wspinaczce w świetnie wyposażonym obiekcie i wspaniałej atmosferze? Zapraszamy do mosiru!',
                                     'Wspinaczka', 'wspinaczka');

-- accounts and profiles for coaches, password is bcrypted (10 cost factor) string "password" in each case
insert into MOSIR_USER values (1, 'Marszałkowska', '21 m.10', 'Warszawa', '1987-12-14', 'andrzej.andrzejowicz@mosir.pl', 'Andrzej', 'Andrzejowicz',
                               '92112341213', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '523512351', 'a.andrzejowicz', '12-412');
insert into COACH values (1, true, 'andrzej-andrzejowicz', 1);
insert into ACTIVITY_TYPE_COACHES values (1, 1);
insert into ACTIVITY_TYPE_COACHES values (3, 1);
insert into ACTIVITY_TYPE_COACHES values (4, 1);
insert into ACTIVITY_TYPE_COACHES values (5, 1);
insert into ACTIVITY_TYPE_COACHES values (7, 1);
insert into MOSIR_USER values (2, 'Marszałkowska', '21 m.10', 'Warszawa', '1987-12-14', 'jacek.kowalski@mosir.pl', 'Jacek', 'Kowalski',
                               '87123502581', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '759123860', 'j.kowalski', '12-412');
insert into COACH values (2, false, 'jacek-kowalski', 2);
insert into ACTIVITY_TYPE_COACHES values (1, 2);
insert into ACTIVITY_TYPE_COACHES values (5, 2);
insert into ACTIVITY_TYPE_COACHES values (8, 2);
insert into MOSIR_USER values (3, 'Marszałkowska', '21 m.10', 'Warszawa', '1987-12-14', 'rafal.sekulski@mosir.pl', 'Rafal', 'Sekulski',
                               '87123502582', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '724157436', 'r.sekulski', '12-412');
insert into COACH values (3, false, 'rafal-sekulski', 3);
insert into ACTIVITY_TYPE_COACHES values (2, 3);
insert into ACTIVITY_TYPE_COACHES values (3, 3);
insert into ACTIVITY_TYPE_COACHES values (4, 3);
insert into ACTIVITY_TYPE_COACHES values (6, 3);
insert into MOSIR_USER values (4, 'Marszałkowska', '21 m.10', 'Warszawa', '1987-12-14', 'jan.janowski@mosir.pl', 'Jan', 'Janowski',
                               '87123502583', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '632612356', 'j.janowski', '12-412');
insert into COACH values (4, false, 'jan-janowski', 4);
insert into ACTIVITY_TYPE_COACHES values (1, 4);
insert into ACTIVITY_TYPE_COACHES values (6, 4);
insert into ACTIVITY_TYPE_COACHES values (8, 4);
insert into MOSIR_USER values (5, 'Marszałkowska', '21 m.10', 'Warszawa', '1987-12-14', 'zbigniew.nowak@mosir.pl', 'Zbigniew', 'Nowak',
                               '87123502584', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '642731396', 'z.nowak', '12-412');
insert into COACH values (5, false, 'zbigniew-nowak', 5);
insert into ACTIVITY_TYPE_COACHES values (2, 5);
insert into ACTIVITY_TYPE_COACHES values (3, 5);
insert into ACTIVITY_TYPE_COACHES values (4, 5);
insert into ACTIVITY_TYPE_COACHES values (5, 5);
insert into MOSIR_USER values (6, 'Marszałkowska', '21 m.10', 'Warszawa', '1987-12-14', 'joanna.podolska@mosir.pl', 'Joanna', 'Podolska',
                               '87123502585', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '804928691', 'j.podolska', '12-412');
insert into COACH values (6, false, 'joanna-podolska', 6);
insert into ACTIVITY_TYPE_COACHES values (2, 6);
insert into ACTIVITY_TYPE_COACHES values (4, 6);
insert into ACTIVITY_TYPE_COACHES values (7, 6);
insert into MOSIR_USER values (7, 'Marszałkowska', '21 m.10', 'Warszawa', '1987-12-14', 'maciej.maciejowski@mosir.pl', 'Maciej', 'Maciejowski',
                               '87123502586', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '523512352', 'm.maciejowski', '12-412');
insert into COACH values (7, false, 'maciej-maciejowski', 7);
insert into ACTIVITY_TYPE_COACHES values (1, 7);
insert into ACTIVITY_TYPE_COACHES values (3, 7);
insert into ACTIVITY_TYPE_COACHES values (4, 7);
insert into ACTIVITY_TYPE_COACHES values (5, 7);
insert into MOSIR_USER values (8, 'Marszałkowska', '21 m.10', 'Warszawa', '1987-12-14', 'wojciech.wojciechowski@mosir.pl', 'Wojciech', 'Wojciechowski',
                               '87123502587', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '523512353', 'w.wojciechowski', '12-412');
insert into COACH values (8, false, 'wojciech-wojciechowski', 8);
insert into ACTIVITY_TYPE_COACHES values (3, 8);
insert into ACTIVITY_TYPE_COACHES values (6, 8);
insert into ACTIVITY_TYPE_COACHES values (7, 8);
insert into ACTIVITY_TYPE_COACHES values (8, 8);
insert into MOSIR_USER values (9, 'Marszałkowska', '21 m.10', 'Warszawa', '1987-12-14', 'katarzyna.kurka@mosir.pl', 'Katarzyna', 'Kurka',
                               '87123502588', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '523512354', 'k.kurka', '12-412');
insert into COACH values (9, false, 'katarzyna-kurka', 9);
insert into ACTIVITY_TYPE_COACHES values (1, 9);
insert into ACTIVITY_TYPE_COACHES values (4, 9);
insert into ACTIVITY_TYPE_COACHES values (6, 9);
insert into MOSIR_USER values (10, 'Marszałkowska', '21 m.10', 'Warszawa', '1987-12-14', 'pawel.pawlowski@mosir.pl', 'Paweł', 'Pawłowski',
                               '87123502589', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '523512355', 'p.pawlowski', '12-412');
insert into COACH values (10, false, 'pawel-pawlowski', 10);
insert into ACTIVITY_TYPE_COACHES values (2, 10);
insert into ACTIVITY_TYPE_COACHES values (5, 10);
insert into ACTIVITY_TYPE_COACHES values (6, 10);
insert into ACTIVITY_TYPE_COACHES values (7, 10);
insert into ACTIVITY_TYPE_COACHES values (8, 10);

--discount types, tickets and membershipcards
insert into discount_type values (1, 'normalny', 1);
insert into discount_type values (2, 'ulgowy', 0.8);
insert into discount_type values (3, 'ulgowy', 0.6);
insert into discount_type values (4, 'studencki', 0.5);

insert into ticket values (1, 20, 1);
insert into ticket_discount_types values (1, 1);
insert into ticket_discount_types values (1, 3);
insert into ticket values (2, 16, 2);
insert into ticket_discount_types values (2, 1);
insert into ticket values (3, 40, 3);
insert into ticket_discount_types values (3, 1);
insert into ticket_discount_types values (3, 2);
insert into ticket_discount_types values (3, 4);
insert into ticket values (4, 30, 4);
insert into ticket_discount_types values (4, 1);
insert into ticket values (5, 20, 5);
insert into ticket_discount_types values (5, 1);
insert into ticket_discount_types values (5, 3);
insert into ticket_discount_types values (5, 4);
insert into ticket values (6, 50, 6);
insert into ticket_discount_types values (6, 1);
insert into ticket_discount_types values (6, 2);
insert into ticket values (7, 24, 7);
insert into ticket_discount_types values (7, 1);
insert into ticket_discount_types values (7, 3);
insert into ticket values (8, 30, 8);
insert into ticket_discount_types values (8, 1);
insert into ticket_discount_types values (8, 2);
insert into ticket_discount_types values (8, 4);

insert into membership_card values (1, 140, 10, 1);
insert into membership_card_discount_types values (1, 1);
insert into membership_card_discount_types values (1, 3);
insert into membership_card values (2, 200, 20, 1);
insert into membership_card_discount_types values (2, 1);
insert into membership_card_discount_types values (2, 3);
insert into membership_card values (3, 100, 10, 2);
insert into membership_card_discount_types values (3, 1);
insert into membership_card values (4, 200, 8, 3);
insert into membership_card_discount_types values (4, 1);
insert into membership_card_discount_types values (4, 2);
insert into membership_card_discount_types values (4, 4);
insert into membership_card values (5, 120, 5, 4);
insert into membership_card_discount_types values (5, 1);
insert into membership_card values (6, 200, 10, 4);
insert into membership_card_discount_types values (6, 1);
insert into membership_card values (7, 100, 8, 5);
insert into membership_card_discount_types values (7, 1);
insert into membership_card_discount_types values (7, 3);
insert into membership_card_discount_types values (7, 4);
insert into membership_card values (8, 100, 3, 6);
insert into membership_card_discount_types values (8, 1);
insert into membership_card_discount_types values (8, 2);
insert into membership_card values (9, 200, 8, 6);
insert into membership_card_discount_types values (9, 1);
insert into membership_card_discount_types values (9, 2);
insert into membership_card values (10, 100, 8, 7);
insert into membership_card_discount_types values (10, 1);
insert into membership_card_discount_types values (10, 3);
insert into membership_card values (11, 100, 5, 8);
insert into membership_card_discount_types values (11, 1);
insert into membership_card_discount_types values (11, 2);
insert into membership_card_discount_types values (11, 4);
insert into membership_card values (12, 180, 10, 8);
insert into membership_card_discount_types values (12, 1);
insert into membership_card_discount_types values (12, 2);
insert into membership_card_discount_types values (12, 4);

--halls
insert into hall values (1, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Basen rekreacyjny', '101B', 'basen-rekreacyjny',
                          'Basen sportowy', '101A', 'basen-sportowy', 1);
insert into hall values (2, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                          'Basen rekreacyjny', '101B', 'basen-rekreacyjny', 1);
insert into hall values (3, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Mała sala do jogi (wewnątrz)', '201', 'sala-do-jogi-mala-wewnatrz', 2);
insert into hall values (4, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Duża sala do jogi (wewnątrz)', '113', 'sala-do-jogi-duza-wewnatrz', 2);
insert into hall values (5, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Ogród do jogi (na zewnątrz)', '005', 'sala-do-jogi-zewnatrz', 2);
insert into hall values (6, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Stoły do tenisa stolowego', '202', 'sala-do-tenisa-stolowego', 3);
insert into hall values (7, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Stadion lekkoatletyczny', '001', 'sala-do-lekkoatletyki-zewnatrz', 4);
insert into hall values (8, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Hala do lekkiej atletyki', '104', 'sala-do-lekkoatletyki-wewnatrz', 4);
insert into hall values (9, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Siłownia', '102', 'silownia', 5);
insert into hall values (10, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Sala do szermierki', '103', 'sala-do-szermierki', 6);
insert into hall values (11, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Boisko do tenisa (na zewnątrz)', '003', 'sala-do-tenisa-zewnatrz', 7);
insert into hall values (12, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Hala do tenisa (wewnątrz)', '203', 'sala-do-tenisa-wewnatrz', 7);
insert into hall values (13, 'Integer id ligula quam. Sed et eros a tortor maximus aliquet eu ut ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent laoreet nibh vitae turpis blandit condimentum. Phasellus sit amet erat non velit elementum posuere.',
                         'Ścianka wspinaczkowa', '105', 'sala-do-wspinaczki', 8);

--sports equipments
insert into sports_equipment values (1, 'SwimDesk 2000', 'Deska do pływania', '1111111111', 'swimdesk-2000', 1);
insert into sports_equipment values (2, 'SwimDesk 3000', 'Deska do pływania', '1111111112', 'swimdesk-3000', 1);
insert into sports_equipment values (3, 'SwimDesk Pro 5000', 'Deska do pływania', '1111111113', 'swimdesk-pro-5000', 1);
insert into sports_equipment values (4, 'SwimGlass A12', 'Okulary do pływania', '1111111121', 'swimglass-a12', 1);
insert into sports_equipment values (5, 'SwimFlippers 200 Adult', 'Płetwy', '1111111131', 'swimflippers-200-adult', 1);
insert into sports_equipment values (6, 'SwimFlippers 200 Kid', 'Płetwy', '1111111132', 'swimflippers-200-kid', 1);
insert into sports_equipment values (7, ' YogMat SM', 'Mata do jogi (mała)', '2111111111', 'yogmat-sm', 2);
insert into sports_equipment values (8, ' YogMat XL', 'Mata do jogi (duża)', '2111111112', 'yogmat-xl', 2);
insert into sports_equipment values (9, ' TableRackets 100 basic', 'Paletki do tenisa stołowego', '3111111111', 'tablerackets-100-basic', 3);
insert into sports_equipment values (10, ' TableRackets 200 basic', 'Paletki do tenisa stołowego', '3111111112', 'tablerackets-200-basic', 3);
insert into sports_equipment values (11, ' TableRackets 200 pro', 'Paletki do tenisa stołowego', '3111111113', 'tablerackets-200-pro', 3);
insert into sports_equipment values (12, ' TableBall', 'Piłeczka do tenisa stołowego', '3111111121', 'tableball', 3);
insert into sports_equipment values (13, ' Table 98', 'Stół do tenisa stołowego', '3111111131', 'table-98', 3);
insert into sports_equipment values (14, ' Table 99', 'Stół do tenisa stołowego', '3111111132', 'table-99', 3);
insert into sports_equipment values (15, ' Timer sport', 'Stoper', '4111111111', 'timer-sport', 4);
insert into sports_equipment values (16, ' Jumpers 210', 'Płotki do skakania', '4111111121', 'jumpers-210', 4);
insert into sports_equipment values (17, ' ThrowBall 400', 'Kula', '4111111131', 'throwball-400', 4);
insert into sports_equipment values (18, ' ThrowHammer 400', 'Młot', '4111111141', 'throwball-400', 4);
insert into sports_equipment values (19, ' JumpStick 400', 'Tyczka', '4111111151', 'jumpstick-400', 4);
insert into sports_equipment values (20, ' Dumbbell 1kg', 'Hantle 1kg', '5111111111', 'dumbbell-1kg', 5);
insert into sports_equipment values (21, ' Dumbbell 2kg', 'Hantle 2kg', '5111111112', 'dumbbell-2kg', 5);
insert into sports_equipment values (22, ' Dumbbell 5kg', 'Hantle 5kg', '5111111113', 'dumbbell-5kg', 5);
insert into sports_equipment values (23, ' Helmet 300', 'Kask', '6111111111', 'helmet-300', 6);
insert into sports_equipment values (24, ' Fencing Suit 300S', 'Kombinezon rozm. S', '6111111121', 'fencing-suit-300s', 6);
insert into sports_equipment values (25, ' Fencing Suit 300M', 'Kombinezon rozm. M', '6111111122', 'fencing-suit-300m', 6);
insert into sports_equipment values (26, ' Fencing Suit 300L', 'Kombinezon rozm. L', '6111111123', 'fencing-suit-300l', 6);
insert into sports_equipment values (27, ' Fencing Sword 45cm', 'Miecz 45cm', '6111111131', 'fencing-sword-45cm', 6);
insert into sports_equipment values (28, ' Fencing Sword 60cm', 'Miecz 60cm', '6111111132', 'fencing-sword-60cm', 6);
insert into sports_equipment values (29, ' TennisBall', 'Piłka do tenisa', '7111111111', 'tennisball', 7);
insert into sports_equipment values (30, ' TennisRackets 190', 'Paletki do tenisa', '7111111121', 'tennisrackets-190', 7);
insert into sports_equipment values (31, ' TennisRackets 190xPRO', 'Paletki do tenisa', '7111111122', 'tennisrackets-190-xpro', 7);
insert into sports_equipment values (32, ' TennisMachine', 'Wyrzutnia piłek', '7111111131', 'tennismachine', 7);
insert into sports_equipment values (33, ' TennisMachine xSPEED', 'Wyrzutnia piłek', '7111111132', 'tennismachine-xspeed', 7);
insert into sports_equipment values (35, ' ClimbingChains', 'Łańcuchy do wspinaczki', '8111111111', 'climbing-chains', 8);
insert into sports_equipment values (36, ' ClimbingHelmet', 'Kask', '8111111121', 'climbing-helmet', 8);

--app user account, password is bcrypted (10 cost factor) string "password"
insert into MOSIR_USER values (101, 'Grochowska', '22 m.9', 'Warszawa', '1998-10-07', 'marcin98@gmail.com', 'Marcin', 'Marciniak',
                               '91127341213', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '503512951', 'marcin98', '11-402');
insert into CLIENT values (1, 101);
insert into MOSIR_USER values (102, 'Test', 'test', 'Test', '1998-10-07', 'test@gmail.com', 'Test', 'Test',
                               '91127347213', '$2y$10$qxyAzlGlejzAaWKDT547qunIoNfp/KOk1obBaDyQYlWx2XeeRpShK', '503502951', 'test', '10-402');
insert into CLIENT values (2, 102);

--app user tickets
insert into CLIENT_TICKET values (1, '2022-11-21', false, '2022-12-21', 1, 1, 1);
insert into CLIENT_TICKET values (2, '2022-11-22T11:52:13', false, '2022-12-22T11:52:13', 1, 1, 1);

--app user membership cards
insert into CLIENT_MEMBERSHIP_CARD values (1, 2, '2022-10-17', '2023-10-17', 1, 4, 2);










