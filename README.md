## Пример реализации сервиса JSON-RPC на фреймоворке Vert.x

Протокол json-rpc 2.0

URI /rpc

Реализовано два режима работы

### Потоковая обработка

#### Методы потоковой обработки

##### add

Добавление одного сообщения

Параметры:

* message - сообщение
```json
"params": {
  "message": {
    "id_sample": "IDS",
    "num_id": "NID",
    "id_location": "IDL",
    "id_signal_par": "ISP",
    "id_detected": "IDD",
    "id_clas_det": "ICD"
  }
}
```

Результат:
```json
"result": "ok"
```

##### getResult

Получение получения результата агрегации

Без параметров

Результат:
```json
"result": [
  {
    "type":"count",
    "value": {
      "id_sample":"IDS",
      "num_id":"NID",
      "id_location":"IDL",
      "id_signal_par":"ISP",
      "count":2
    },
    "messages":[
      {
        "id_sample":"IDS",
        "num_id":"NID",
        "id_location":"IDL",
        "id_signal_par":"ISP",
        "id_detected":"IDD",
        "id_clas_det":"ICD"
      },
      {
        "id_sample":"IDS",
        "num_id":"NID",
        "id_location":"IDL",
        "id_signal_par":"ISP",
        "id_detected":"IDD",
        "id_clas_det":"ICD"
      }
    ]
  }
]
```

### Пакетная обработка

#### Методы пакетной обработки

##### batchProcess

Параметры:

* messages - массив сообщений

```json
"params": {
  "messages":[
    {
      "id_sample":"IDS",
      "num_id":"NID",
      "id_location":"IDL",
      "id_signal_par":"ISP",
      "id_detected":"IDD",
      "id_clas_det":"ICD"
    },
    {
      "id_sample":"IDS",
      "num_id":"NID",
      "id_location":"IDL",
      "id_signal_par":"ISP",
      "id_detected":"IDD",
      "id_clas_det":"ICD"
    }
  ]
}
```

Результат:
```json
"result": [
  {
    "type":"count",
    "value": {
      "id_sample":"IDS",
      "num_id":"NID",
      "id_location":"IDL",
      "id_signal_par":"ISP",
      "count":2
    },
    "messages":[
      {
        "id_sample":"IDS",
        "num_id":"NID",
        "id_location":"IDL",
        "id_signal_par":"ISP",
        "id_detected":"IDD",
        "id_clas_det":"ICD"
      },
      {
        "id_sample":"IDS",
        "num_id":"NID",
        "id_location":"IDL",
        "id_signal_par":"ISP",
        "id_detected":"IDD",
        "id_clas_det":"ICD"
      }
    ]
  }
]
```