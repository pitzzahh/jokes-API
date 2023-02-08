
# Jokes API

API that generates random jokes based on some categories or just random
___
![GitHub Issues](https://img.shields.io/github/issues/pitzzahh/jokes-API)
![Forks](https://img.shields.io/github/forks/pitzzahh/jokes-API)
![Stars](https://img.shields.io/github/stars/pitzzahh/jokes-API)
![License](https://img.shields.io/github/license/pitzzahh/jokes-API)
![Forks](https://img.shields.io/github/forks/pitzzahh/jokes-API)
![Stars](https://img.shields.io/github/stars/pitzzahh/jokes-API)
![Contributors](https://img.shields.io/github/contributors/pitzzahh/jokes-API)
![Last Commit](https://img.shields.io/github/last-commit/pitzzahh/jokes-API)
![Code size](https://img.shields.io/github/languages/code-size/pitzzahh/jokes-API)
![Top language](https://img.shields.io/github/languages/top/pitzzahh/jokes-API)
![Languages count](https://img.shields.io/github/languages/count/pitzzahh/jokes-API)
![Repo size](https://img.shields.io/github/repo-size/pitzzahh/jokes-API)
___
## API Reference

#### Get a random joke and returns a JSON response from the API

##### Temporary link
- https://jokes-api-prod-production.up.railway.app

```html
 GET https://jokes.araopj.tech/v1/random
```

#### Example response

```json
{
  "joke": "What do you call a fake noodle? An \"impasta\".",
  "category": "DAD_JOKE",
  "language": "ENGLISH"
}
```
```json
{
  "joke": "Ano ang sabi ng isang kahoy sa ibang kahoy? Pakapit!",
  "category": "ONE_LINER",
  "language": "FILIPINO"
}
```
#### Get random joke based on category and language

```html
 GET https://jokes.araopj.tech/v1/random?category=${category}&language=${language}
```

| Parameter  | Type     | Description                             |
|:-----------|:---------|:----------------------------------------|
| `category` | `String` | **Required**. category of joke to fetch |
| `language` | `String` | **Required**. language of joke to fetch |


##### Sample Request

```html
 GET https://jokes.araopj.tech/v1/random?category=ANY&lang=FILIPINO
```

#### Get random joke based on category

```html
 GET https://jokes.araopj.tech/v1/random?category=${category}
```

| Parameter  | Type     | Description                             |
|:-----------|:---------|:----------------------------------------|
| `category` | `String` | **Required**. category of joke to fetch |

| Categories <br/>available |
|:--------------------------|
| `ANY`                     |
| `DAD_JOKE`                |
| `PUN`                     |
| `KNOCK_KNOCK`             |
| `ONE_LINER`               |

#### Sample Request

```html
 GET https://jokes.araopj.tech/v1/random?category=DAD_JOKE
```
___

#### Get random joke based on language

```html
 GET https://jokes.araopj.tech/v1/random?language=${language}
```

| Parameter  | Type     | Description                             |
|:-----------|:---------|:----------------------------------------|
| `language` | `String` | **Required**. language of joke to fetch |


| Languages <br/>available |
|:-------------------------|
| `ENGLISH`                |
| `FILIPINO`               |

#### Sample Request

```html
 GET https://jokes.araopj.tech/v1/random?lang=FILIPINO
```
___

## Contributing

Contributions are always welcome!

#### Have a joke in mind? Submit now!

```html
 POST https://api.araopj.tech/api/v1/jokes/submit
```

##### Sample submission

```json
{
  "joke": "What do you call a cow with no legs? Ground beef.",
  "category": "DAD_JOKE",
  "language": "ENGLISH"
}
```
___

See `contributing.md` for ways to get started.

Please adhere to this project's `code of conduct`.

___
## Features
- [x] Random joke
- [x] Many joke categories
- [x] Many joke languages
- [x] Random joke based on category
- [x] Random joke based on language
- [x] Random joke based on category and language
- [x] API Documentation
- [ ] API Rate Limiting
- [ ] API Authentication
- [ ] API Key
- [ ] Microservice (? maybe)
___
## Support

For support, email pitzzahh@araopj.tech
___
## License
[MIT](https://choosealicense.com/licenses/mit/)

### 🤍 Special Thanks to [railway](https://railway.app/) for providing free hosting for this project

