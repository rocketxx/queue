# Spring Boot Order Management System

Questo progetto Spring Boot utilizza **RabbitMQ** per la gestione di una coda di messaggi, la libreria **iText 7** per la generazione di PDF, e il server **SMTP di Google (Gmail)** per l'invio di email con PDF allegato.

Il sistema espone delle API che consentono di inserire un ordine insieme a un'email. Successivamente, il progetto si occupa di elaborare l'ordine, generare un PDF con i dettagli dell'ordine e inviarlo all'email fornita come allegato.

## Funzionalità principali

- **Gestione ordini**: Gli ordini vengono inviati tramite una coda RabbitMQ per essere elaborati in modo asincrono.
- **Generazione PDF**: Per ogni ordine, viene creato un documento PDF contenente i dettagli dell'ordine, inclusi il nome del cliente, l'indirizzo di consegna, il totale e gli articoli.
- **Invio email**: L'ordine in formato PDF viene inviato tramite email (utilizzando il server SMTP di Google) all'indirizzo email fornito dall'utente.

## Requisiti

- **Java 17**
- **Apache Maven 3.9.8**
- **RabbitMQ** (Server di messaggistica)
- **Google SMTP** (Per l'invio di email)

## Configurazione file `application.properties`

```properties
# RabbitMQ Configuration
spring.application.name=queue
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
spring.rabbitmq.virtual-host=/

# Server SMTP Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=tua_mail
spring.mail.password=tua_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```
## API

### `/orders/create`
- **Metodo**: `POST`
- **Descrizione**: Crea un nuovo ordine e invia una email con il PDF allegato.

#### Esempio di richiesta in [Swagger UI](http://localhost:8080/swagger-ui/index.html)

```json
{
  "order": {
    "customerName": "Mario Rossi",
    "deliveryAddress": "Via Roma 68",
    "items": [
      {
        "itemName": "Pizza Margherita",
        "quantity": 1,
        "price": 13
      }
    ],
    "totalPrice": 13,
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa9"
  },
  "email": "tua_mail"
}

```

## Istruzioni per l'esecuzione

### Clonare il progetto, controllare le versioni ed eseguirlo:

```bash
git clone <URL del repository>
cd <cartella del progetto>
```
Esegui l'applicazione
Avvia l'applicazione utilizzando il comando Maven:
```bash
./mvnw spring-boot:run
```
Verifica la versione di Java
Assicurati di avere installato Java 17:
```bash
java -version
```
# java version "17.0.12" 2024-07-16 LTS

Controlla la versione di Maven:
```bash
mvn -v
```
# Apache Maven 3.9.8
