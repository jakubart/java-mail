# Java Mail
> Aplikacja pobiera dane z bazy danych i wysyła je w postaci wiadomości e-mail.

## Opis aplikacji

Program działa równolegle z dwie bazami danych. Z MySql pobiera inormacje na temat id_zamówienia, które ostatnio zostało wysłane do użytkownika. Jeżeli ta wartość jest mniejsza niż największe id_zamowienia w bazie MS SQL Server to aplikacja zbiera infomracje na temat wszystkich zamówien i wysyła je wiadomością email. Po zakończonych operacjach id_zamówienia w MySql jest aktualizowane na wartość ostatniego wysłanego zamówienia.

## Użyte technologie

* Java 8
* javax.mail
* Maven
* SQL

