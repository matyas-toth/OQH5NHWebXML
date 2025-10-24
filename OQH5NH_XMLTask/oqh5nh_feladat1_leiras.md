Állatorvosi rendelő nyilvántartó rendszer

Egyedek és főbb tulajdonságaik:
 Állat (állatID, név, faj, születésiDátum, oltások [többértékű])
 Gazda (gazdaID, név, cím, telefonszám, email)
 Orvos (orvosID, név, szakterület, beosztás)
 Kezelés (kezelésID, dátum, diagnózis, ár)
 Gyógyszer (gyógyszerID, név, hatóanyag, adagolás)
 EgészségügyiKisköny (könyvID, létrehozásDátum, megjegyzés)

Kapcsolatok:
 Gazda - Állat -> (1:N)
 Állat - Kezelés -> (1:N)
 Orvos - kezelés -> (1:N)
 Kezelés - Gyógyszer -> (N:M), kapcsolat tulajdonságai: adag, időtartam
 Állat - EgészségügyiKiskönyv -> (1:1)