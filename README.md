
# Jokes API

API that generates random jokes based on some categories or just random
___

![Logo]()

___
![GitHub Issues](https://img.shields.io/github/issues/pitzzahh/jokes)
![Forks](https://img.shields.io/github/forks/pitzzahh/jokes)
![Stars](https://img.shields.io/github/stars/pitzzahh/jokes)
![License](https://img.shields.io/github/license/pitzzahh/jokes)
___
## API Reference

#### Get a random joke and returns a JSON response from the API

```http
  GET /jokes/random
```

#### Example response

```json
{
  "category": "DAD_JOKE",
  "joke": "What do a tick and the Eiffel Tower have in common? They're both Paris sites."
}
```


#### Get random joke based on category

```http
  GET /jokes/random?category=${category}
```

| Parameter  | Type     | Description                             |
|:-----------|:---------|:----------------------------------------|
| `category` | `String` | **Required**. category of joke to fetch |

___
## Contributing

Contributions are always welcome!

See `contributing.md` for ways to get started.

Please adhere to this project's `code of conduct`.

___
## Features
- ~~Many joke categories~~
___
## Support

For support, email pitzzahh@gmail.com
___
## License
[MIT](https://choosealicense.com/licenses/mit/)

