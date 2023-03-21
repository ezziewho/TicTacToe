## Zadania do wykonania
1. Zainstalowano klienta Git i obsługę kluczy SSH
2. Sklonowano repozytorium https://github.com/InzynieriaOprogramowaniaAGH/MDO2023_S za pomocą HTTPS
![sklonowanieHTTPS](./Screenshots/sklonowanieHTTPS.png)
3. Upewniono się się w kwestii dostępu do repozytorium jako uczestnik i sklonowano je za pomocą utworzonego klucza SSH
   - Utworzono dwa klucze SSH, inne niż RSA, w tym co najmniej jeden zabezpieczony hasłem
   ![generacja_klucza1](./Screenshots/generacja_klucza1.png)
   ![generacja_klucza2](./Screenshots/generacja_klucza2.png)
   - Skonfigurowano klucz SSH jako metodę dostępu do GitHuba
   ![metoda_dostepuGIT](./Screenshots/metoda_dostepuGIT.png)
   - Sklonowano repozytorium z wykorzystaniem protokołu SSH
   ![sklonowanieSSH](./Screenshots/sklonowanieSSH.png)
4. Przełączono się na gałąź ```main```, a potem na gałąź swojej grupy (pilnowano gałęzi i katalogu)
![main](./Screenshots/main.png)
5. Utwórz gałąź o nazwie "inicjały & nr indeksu" np. Miej na uwadze, że odgałęziasz się od brancha grupy!
![tworzenie_wlasnego_brancha](./Screenshots/tworzenie_wlasnego_brancha.png)
6. Rozpoczęto pracę na nowej gałęzi
   - W katalogu właściwym dla grupy utworzono nowy katalog, także o nazwie "inicjały & nr indeksu" 
   ![praca_na_branchu](./Screenshots/praca_na_branchu.png)
   ![tworzenie_wlasnego_brancha2](./Screenshots/tworzenie_wlasnego_brancha2.png)
   - Napisano Git hook'a - skrypt weryfikujący, że każdy "commit message" zaczyna się od "inicjały & nr indexu"
   ![git_hook](./Screenshots/git_hook.png)
   - Dodano ten skrypt do stworzonego wcześniej katalogu.
   ![utworzenie_hooka](./Screenshots/utworzenie_hooka.png)
   - (W międzyczasie w celu utrzymania porządku w miejscu pracy przeniesiono repozytorium do innego folderu - stąd zmiana ścieżki)
   - Skopiowano go we właściwe miejsce, tak by uruchamiał się za każdym razem kiedy robi się commita.
   ![kopiowanie_commita](./Screenshots/kopiowanie_commita.png)
   W katalogu dodano plik ze sprawozdaniem oraz zrzutami ekranu.
   ![dodanie_sprawozdania](./Screenshots/dodanie_sprawozdania.png)
7. Wysłano zmiany do zdalnego źródła, oraz wystawiono Pull Request do gałęzi grupowej.
![pushowanie](./Screenshots/pushowanie.png)


