# Java Mail - automat
> Aplikacja pobiera dane z bazy i rozsyla je w postaci wiadomości e-mail.

## Opis aplikacji

Program działa równolegle z dwie bazami danych. Z MySql pobiera inormacje na temat id_zamówienia, które ostatnio zostało wysłane do użytkownika. Jeżeli ta wartość jest mniejsza niż najwieksze id_zamowienia w bazie MS SQL Server to aplikacja zbiera infomracje na temat wszystkich zamówien i wysyła je wiadomością email. Po zakończonych operacjach id_zamówienia w MySql jest aktualizowane na wartość ostatniego wysłanego zamówienia.

## Użyte technologie

* Java 8
* javax.mail
* Maven

## Opis uruchomienia

W pliku apli
