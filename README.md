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

---

## Útgáfur (Releases)

### Version 1.0 – Notendaprófanir (User Acceptance Testing)

Þetta er útgáfa sem er tilbúin til notendaprófana.

**Búa til Version 1.0 Release og Tag á GitHub:**

```bash
# Tagga kóðann
git tag -a v1.0 -m "Version 1.0 - Tilbúið til notendaprófana"
git push origin v1.0
```

Síðan á GitHub:

1. Farðu í **Releases** → **Draft a new release**
2. Veldu tagið `v1.0`
3. Settu titilinn: `Version 1.0 – Notendaprófanir`
4. Lýstu hvað er tilbúið og hvaða hlutir eru til skoðunar
5. Smelltu á **Publish release**

---

### Version 2.0 – Lokaútgáfa

Þetta er lokaútgáfa forritsins að verkefni loknu.

**Búa til Version 2.0 Release og Tag á GitHub:**

```bash
# Tagga kóðann
git tag -a v2.0 -m "Version 2.0 - Lokaútgáfa"
git push origin v2.0
```

Síðan á GitHub:

1. Farðu í **Releases** → **Draft a new release**
2. Veldu tagið `v2.0`
3. Settu titilinn: `Version 2.0 – Lokaútgáfa`
4. Lýstu öllum breytingum frá v1.0 og hvað var lagað eða bætt
5. Smelltu á **Publish release**

---

## Uppfærslur á verkefnum (valkvætt)

**Til að missa ekki af nýjum vikum:**
- Smelltu á **Watch → All activity** á GitHub repo-inu  
  Þá færðu tilkynningu þegar ný vika (Release) er sett inn.

**Finna rétt verkefni:**
- Hvert verkefni er merkt með **Release + Tag** (t.d. `Verkefni-1`)
- Þú getur skoðað eldri útgáfur undir *Releases* á GitHub

**Ef þú ert með fork:**
```bash
git fetch upstream
git merge upstream/main
```