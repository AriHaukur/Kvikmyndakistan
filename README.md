# Kvikmyndakistan-verkefni-2026
Áfangaverkefnið okkar í námskeiðinu HBV201G Viðmótsforritun 2026.

Skipulagt eftir verkefnum. Þetta repo er uppfært eftir að lausn á verkefni hefur verið birt eða uppfærð.  
Hvert verkefni er opnað sem sérstakt project í IntelliJ þannig að mappan fyrir verkefnið sé rótin.

---

## Byggt og keyrt með Maven

### Forsendur

- **Java 25** eða nýrra uppsett á tölvunni
- **Maven 3.8+** uppsett og aðgengilegt í terminal (`mvn -version` til að staðfesta)
- Nettenging til að sækja Maven-pakka í fyrsta skipti

---

### Að byggja forritið

Farðu í rótarmöppu verkefnisins (þar sem `pom.xml` er) og keyrðu:

```bash
mvn clean package
```

Þetta þýðir kóðann og setur saman allt sem þarf. Ef allt gengur upp sérðu `BUILD SUCCESS` í terminal.

---

### Að keyra forritið

Til að ræsa JavaFX forritið:

```bash
mvn clean javafx:run
```

> **Athugið:** `clean` hreinsar gömlar þýðingarskrár áður en forritið er keyrt, til að forðast óvæntar villur.

---

### Villuleit / Debug

Til að keyra forritið í debug-stillingu (port 5005):

```bash
mvn javafx:run@debug
```

Tengdu síðan debugger í IntelliJ við `localhost:5005`.

---

### Keyrsla í IntelliJ

1. Opnaðu verkefnismöppuna sem project í IntelliJ (`File → Open`)
2. IntelliJ þekkir `pom.xml` sjálfkrafa og setur upp Maven
3. Notaðu Maven-gluggann (hægri hlið) → `Plugins → javafx → javafx:run`  
   eða keyrðu `mvn clean javafx:run` í innbyggðum terminal


**Ef þú ert með fork:**
```bash
git fetch upstream
git merge upstream/main
```
