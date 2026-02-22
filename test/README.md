# Na Lovu - Textová adventura

**Autor:** Jakub Griz

---

##  Úvod a příběh hry
**Na Lovu** je klasická textová adventura, ve které hráč ovládá hlavní postavu pomocí textových příkazů.

Jsi Jindřich a tvým cílem je dostat se z hluboké divočiny zpět do civilizace. K tomu potřebuješ přesvědčit potulného šarlatána Huberta, aby tě vzal s sebou svým vozem. Ten ovšem bez takzvaného "kočičího zlata" (pyritu) nikam neodjede. Musíš prozkoumat okolí, pomoci místním obyvatelům a pomocí promyšlených směn a úkolů získat to, co potřebuješ k cestě domů.

##  Herní svět a postavy
Během svého putování narazíš na několik unikátních lokací a postav, se kterými musíš spolupracovat:
* **Kořenářka Květuše:** Zkušená bylinkářka. Potřebuje pomoct se sběrem bylin, za které tě štědře odmění jídlem.
* **Hledač Gerald:** Pracuje u potoka a hledá drahé kamení. Je unavený a hladový. Zlato ti nedá zadarmo, musíš mu donést zásoby.
* **Strážný Vítek:** Hlídá okolí, ale nudí se, protože prohrál své hrací kostky.
* **Šarlatán Hubert:** Tvoje jediná jízdenka domů. Čeká v táboře a zajímá ho jen zisk.

##  Herní mechaniky
Hra obsahuje několik zajímavých systémů:
* **Pohyb a průzkum:** Svět je rozdělen do navzájem propojených lokací (Rozcestí, Temný les, Louka, Tábor atd.).
* **Inventář:** Hráč může sbírat předměty, nosit je u sebe a používat je k plnění úkolů.
* **Systém úkolů (Questů):** Postavy si pamatují, jaké předměty chtějí. Po jejich odevzdání dají hráči odměnu (např. Dýku, Klobásu nebo Pyrit) a změní svůj dialog.
* **Crafting (Kombinování předmětů):** Některé předměty vyžadují k sebrání nástroj. K natrhání bylin na louce hráč musí nejprve někde najít a mít v inventáři prázdný košík.

---

##  Herní příkazy
Hra se ovládá zadáváním klíčových slov a parametrů do konzole:

* `pohyb [lokace]` -> Přesune tě do sousední místnosti (např. `pohyb les`)
* `sebrat [věc]` -> Sebere předmět ze země (např. `sebrat kosik`)
* `mluvit [postava]` -> Promluví s postavou a případně jí předá předměty z inventáře (např. `mluvit kvetuse`)
* `inventar` -> Zobrazí obsah tvého batohu
* `pomoc` -> Zobrazí herní nápovědu a seznam příkazů
* `konec` -> Okamžitě ukončí hru

---

##  Průběh hry 
Pokud by ses ve hře zasekl, zde je postup, jak ji úspěšně dohrát:

1. **Příprava:** Najdi v herním světě prázdný košík a seber ho (`sebrat kosik`).
2. **Sběr bylin:** Jdi na rozkvetlou louku a nasbírej léčivé byliny (`sebrat kytky`). Díky košíku v inventáři získáš "Košík plný bylin".
3. **Zásoby v osadě:** V uhlířské osadě nezapomeň sebrat pivo (`sebrat pivo`).
4. **Odměna od kořenářky:** Jdi do chatrče za Květuší a promluv s ní (`mluvit kvetuse`). Vezme si košík s bylinami a dá ti dobrou klobásu.
5. **Zlato od Geralda:** Jdi k potoku za hledačem Geraldem. Když u sebe budeš mít v inventáři pivo i klobásu, promluv s ním (`mluvit gerald`). Dá ti hledaný pyrit.
6. **Konec hry:** Dones pyrit šarlatánu Hubertovi do tábora (`mluvit hubert`). Tím splníš hlavní úkol a zachráníš se!
7. *(Vedlejší úkol):* Můžeš také najít ztracené hrací kostky a donést je strážnému Vítkovi (`mluvit vitek`), za což jako speciální odměnu získáš loveckou dýku.

---

##  Technické řešení a spuštění
Projekt je napsán v jazyce **Java** a využívá architekturu založenou na návrhovém vzoru *Command* pro zpracování uživatelských příkazů.

* **Načítání dat:** Herní svět (lokace, předměty, vlastnosti postav a dialogy) není zapsán natvrdo v kódu, ale dynamicky se načítá ze souboru `gamedata.json` pomocí knihovny **Google Gson**. To umožňuje snadnou úpravu světa bez nutnosti zásahu do zdrojových kódů.
* **Testování:** Klíčové herní mechaniky (funkčnost lokací, správa inventáře a logika craftingu) jsou pokryty automatizovanými jednotkovými testy pomocí frameworku **JUnit**.

**Jak projekt spustit:**
1. Otevři projekt ve svém oblíbeném IDE (např. IntelliJ IDEA).
2. Ujisti se, že máš v projektu správně importovanou knihovnu Gson a framework JUnit.
3. Spusť hlavní třídu obsahující metodu `main` (obvykle ve třídě `Hra` nebo `Main`).