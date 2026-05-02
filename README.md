# Aplicație Web cu Servlet-uri Java EE — Laborator 1 Sisteme Distribuite

Aceasta este o aplicație web Java EE (Jakarta EE) dezvoltată ca o versiune modificată a laboratorului 1 de **Sisteme Distribuite**. Aplicația demonstrează utilizarea **Servlet-urilor**, **JSP-urilor** și a unei baze de date **SQLite** pentru gestionarea unei liste de studenți.

---

## Funcționalități

- **Adăugare student** — formular HTML care trimite date (nume, prenume, vârstă) printr-un request POST
- **Vizualizare studenți** — afișarea tuturor studenților existenți în baza de date
- **Căutare după nume** — filtrarea studenților din baza de date pe baza numelui introdus
- **Export JSON** — generarea unui fișier JSON cu toți studenții și afișarea conținutului în browser

---

## Structura proiectului

```
src/
└── main/
    ├── java/
    │   ├── HelloServlet.java           # Servlet de test ("Hello from servlet")
    │   ├── ProcessStudentServlet.java  # Prelucrează formularul de adăugare student (POST)
    │   ├── ReadStudentServlet.java     # Citește toți studenții din DB și generează JSON
    │   ├── SearchNameServlet.java      # Caută studenți după nume (POST)
    │   ├── JsonMakerServlet.java       # Generează și afișează JSON-ul studenților
    │   ├── SqlWorkClass.java           # Logica de acces la baza de date SQLite
    │   └── beans/
    │       └── StudentBean.java        # JavaBean pentru un student (nume, prenume, vârstă, an naștere)
    └── webapp/
        ├── index.jsp                   # Pagina principală cu linkuri
        ├── formular.jsp                # Formular adăugare student
        ├── formular-cautare.jsp        # Formular căutare după nume
        ├── info-student.jsp            # Afișare listă studenți
        ├── json-students.jsp           # Afișare JSON studenți
        └── WEB-INF/
            └── web.xml                 # Configurare servlet-uri și URL mapping
```

---

## Tehnologii utilizate

| Tehnologie            | Versiune  | Rol                                    |
|-----------------------|-----------|----------------------------------------|
| Java EE (Jakarta EE)  | 8         | Platforma de bază                      |
| Java                  | 8         | Limbajul de programare                 |
| Servlet API           | —         | Procesarea cererilor HTTP              |
| JSP (JavaServer Pages)| —         | Generare conținut HTML dinamic         |
| SQLite (via JDBC)     | 3.51.2    | Baza de date locală                    |
| Jackson (JSON)        | 2.11.4    | Serializare/deserializare JSON         |
| Maven                 | —         | Build tool și gestionare dependențe    |
| GlassFish             | 5.x       | Server de aplicații JEE                |

---

## URL-uri disponibile

| URL                    | Metodă | Descriere                                      |
|------------------------|--------|------------------------------------------------|
| `/`                    | GET    | Pagina principală (index.jsp)                  |
| `/hello`               | GET    | Servlet de test                                |
| `/formular.jsp`        | GET    | Formular adăugare student                      |
| `/process-student`     | POST   | Procesează adăugarea unui student în DB        |
| `/read-student`        | GET    | Afișează toți studenții și generează JSON      |
| `/formular-cautare.jsp`| GET    | Formular căutare după nume                     |
| `/search-name`         | POST   | Caută studenți după nume în DB                 |
| `/json-maker`          | GET    | Afișează JSON-ul generat din baza de date      |

---

## Cerințe preliminare

- **Java 8** sau mai nou
- **Maven 3.x**
- **GlassFish 5.x** instalat local
- Drepturi de scriere la calea unde se salvează baza de date SQLite și fișierul JSON

---

## Configurare

Înainte de build, actualizați căile absolute din cod cu cele corespunzătoare sistemului vostru:

1. **`SqlWorkClass.java`** — calea către fișierul bazei de date SQLite:
   ```java
   String path = "/calea/catre/students.db";
   ```

2. **`JsonMakerServlet.java`** și **`SqlWorkClass.java`** — calea unde se salvează fișierul JSON:
   ```java
   new File("/calea/catre/studentiJson.json");
   new FileWriter("/calea/catre/studentiJson.json");
   ```

3. **`pom.xml`** — calea de instalare GlassFish:
   ```xml
   <home>/calea/catre/glassfish5</home>
   ```

---

## Build și deploy

```bash
# Build proiect
mvn clean package

# Deploy pe GlassFish (necesită GlassFish pornit)
mvn cargo:deploy
```

Aplicația va fi disponibilă la:
```
http://localhost:8080/JEE-Test/
```

---

## Model de date — Student

| Câmp        | Tip     | Descriere                          |
|-------------|---------|------------------------------------|
| `NUME`      | STRING  | Numele de familie al studentului   |
| `PRENUME`   | STRING  | Prenumele studentului              |
| `VARSTA`    | INTEGER | Vârsta studentului                 |
| `AN_NASTERE`| INTEGER | Calculat automat: an curent - vârstă |

---

## Notă academică

Acest proiect a fost realizat în cadrul cursului de **Sisteme Distribuite** și reprezintă o versiune extinsă a laboratorului 1, adăugând funcționalități de persistență (SQLite), căutare și export JSON față de varianta inițială bazată pe servlet-uri simple.
