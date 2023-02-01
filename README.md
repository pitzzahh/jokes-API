
# Jokes API

API that generates random jokes based on some categories or just random
___

![Logo]()

___
![GitHub Issues](https://img.shields.io/github/issues/pitzzahh/jokes-API)
![Forks](https://img.shields.io/github/forks/pitzzahh/jokes-API)
![Stars](https://img.shields.io/github/stars/pitzzahh/jokes-API)
![License](https://img.shields.io/github/license/pitzzahh/jokes-API)
___
## API Reference

#### Get a random joke and returns a JSON response from the API

```html
 GET https://api.araopj.tech/api/v1/jokes/random
```

#### Example response

```json
{
  "joke": "What do you call a fake noodle? An \"impasta\".",
  "category": "DAD_JOKE",
  "lang": "ENGLISH"
}
```
```json
{
  "joke": "Ano ang sabi ng isang kahoy sa ibang kahoy? Pakapit!",
  "category": "ONE_LINER",
  "lang": "FILIPINO"
}
```
#### Get random joke based on category and language

```html
 GET https://api.araopj.tech/api/v1/jokes/random?category=${category}&lang=${language}
```

| Parameter  | Type     | Description                             |
|:-----------|:---------|:----------------------------------------|
| `category` | `String` | **Required**. category of joke to fetch |
| `lang`     | `String` | **Required**. language of joke to fetch |


#### Get random joke based on category

```html
 GET https://api.araopj.tech/api/v1/jokes/random?category=${category}
```

| Parameter  | Type     | Description                             |
|:-----------|:---------|:----------------------------------------|
| `category` | `String` | **Required**. category of joke to fetch |

___

#### Get random joke based on language

```html
 GET https://api.araopj.tech/api/v1/jokes/random?lang=${language}
```

| Parameter | Type     | Description                             |
|:----------|:---------|:----------------------------------------|
| `lang`    | `String` | **Required**. language of joke to fetch |

___
## Contributing

Contributions are always welcome!

See `contributing.md` for ways to get started.

Please adhere to this project's `code of conduct`.

___
## Features
- [ ] Many joke categories
- [ ] Many joke languages
___
## Support

For support, email pitzzahh@araopj.tech
___
## License
[MIT](https://choosealicense.com/licenses/mit/)

