insert into "bolnica"("id", "naziv", "adresa", "budzet")
values (nextval('bolnica_seq'), 'KBC Zemun', 'Vukova 9', 200000);

insert into "bolnica"("id", "naziv", "adresa", "budzet")
values (nextval('bolnica_seq'), 'KC Vojvodine', 'Hajduk Veljkova 1', 30000);

insert into "bolnica"("id", "naziv", "adresa", "budzet")
values (nextval('bolnica_seq'), 'Urgentni centar', 'Hajduk Veljkova 1', 30000);

insert into "bolnica"("id", "naziv", "adresa", "budzet")
values (nextval('bolnica_seq'), 'Institut za plucne bolesti', 'Stevana Goldmana', 500000);

insert into "bolnica"("id", "naziv", "adresa", "budzet")
values (nextval('bolnica_seq'), 'Institut za zdravstvenu zastitu dece i omladine', 'Hajduk Veljkova 10', 30000);




insert into "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Upala bubrega', 'Upala bubrega', 176);

insert into "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Aritmije', 'Aritmije', 19);

insert into "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Operacija slepog creva', 'Operacija slepog creva', 91);

insert into "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Astma', 'Astma', 3);

insert into "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Deformitet stopala', 'Deformitet stopala', 25);



insert into "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Nefrologija', '11A', 1);

insert into "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Kardiologija', '119', 2);

insert into "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Hirurgija', '00B', 3);

insert into "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Pulmologija', '5', 4);

insert into "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Ortopedija', 'AA1', 5);




insert into "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Aleksandar', 'Jovic', false, to_date('03.06.2021.' , 'dd.mm.yyyy.'), 1, 1);

insert into "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Ana', 'Mijic', true, to_date('07.09.2021.' , 'dd.mm.yyyy.'), 2, 2);

insert into "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Stefan', 'Lazic', true, to_date('03.06.2021.' , 'dd.mm.yyyy.'), 3, 3);

insert into "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Milica', 'Kocic', true, to_date('02.01.2022.' , 'dd.mm.yyyy.'), 4, 4);

insert into "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Sasa', 'Simic', false, to_date('04.01.2022.' , 'dd.mm.yyyy.'), 5, 5);

