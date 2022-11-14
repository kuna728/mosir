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
                         'Sala do jogi', '112', 'sala-do-jogi', 2);









