# TruckManagement

## Unit entity

### List of units resource

URI: /unit

|  Method  |  Action  | Params | 
| ------------ | ------------ |------------ |
| GET | Gets list of units. |||
| POST | Creates unit and gets created entity. |unit_name - string, contain unit's name  |

### Unit resource
URI: /unit/```{id}```
|  Method  |  Action  | Params|
| ------------ | ------------ |------------ |
| GET | Gets unit by its ```{id}```. ||
| PUT | Updates unit and gets updated entity with ```{id}```. |unit_name - string, contain unit's name  |
## Brand entity
### List of brands resource

URI: /brand

|  Method  |  Action  | Params|
| ------------ | ------------ |------------ |
| GET | Gets list of brands. ||
| POST | Creates brand and gets created entity. |brand_name - string, contain brand's name  |

### Brand resource
URI: /brand/```{id}```
|  Method  |  Action  | Params|
| ------------ | ------------ |------------ |
| GET | Gets brand by its ```{id}```. ||
| PUT | Updates unit and gets updated entity with ```{id}```. |(At least one parameter) unit_name - string, contain brand's name  |
## Bodywork entity
### List of bodywork resource

URI: /bodywork

|  Method  |  Action  | Params  |
| ------------ | ------------ |------------ |
| GET | Gets list of bodyworks. ||
| POST | Creates bodywork and gets created entity. |(All parameters mandatory) model - string, contain model's info |
|  |  |brand_id - int, contain brand's id in database |

### Bodywork resource
URI: /bodywork/```{id}```
|  Method  |  Action  | Params|
| ------------ | ------------ |------------ |
| GET | Gets bodywork by its ```{id}```. ||
| PUT | Updates bodywork and gets updated entity with ```{id}```. |(At least one parameter) model - string, contain model's info |At least one parameter
|  |  |brand_id - int, contain brand's id in database |

## Engine entity
### List of engine resource

URI: /engine

|  Method  |  Action  | Params  |
| ------------ | ------------ |------------ |
| GET | Gets list of engines. ||
| POST | Creates engine and gets created engine. |(All parameters mandatory)name - string, contain engine's name | 
|  |  |power - int, contain engine's power |
|  |  |unit_id - int, contain unit's id in database |

### Engine resource
URI: /engine/```{id}```
|  Method  |  Action  | Params|
| ------------ | ------------ |------------ |
| GET | Gets engine by its ```{id}```. ||
| PUT | Updates engine and gets updated entity with ```{id}```. |(At least one parameter) name - string, contain engine's name |
|  |  |power - int, contain engine's power |
|  |  |unit_id - int, contain unit's id in database |

## Car entity
### List of car resource

URI: /car

|  Method  |  Action  | Params  |
| ------------ | ------------ |------------ |
| GET | Gets list of cars. ||
| POST | Creates car and gets created car. |(All parameters mandatory) engine_id - int, contain engine's id in database | 
||  |bodywork - int, contain bodywork's id in database |

### Car resource
URI: /car/```{id}```
|  Method  |  Action  | Params|
| ------------ | ------------ |------------ |
| GET | Gets car by its ```{id}```. ||
| PUT | Updates car and gets updated entity with ```{id}```. |(At least one parameter) engine_id - int, contain engine's id in database |
|  |  |bodywork - int, contain bodywork's id in database |
