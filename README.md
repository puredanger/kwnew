# kwnew

Tests for keyword performance. Probably not too useful for anyone but me.

## Usage

Start a repl:
```
lein repl    ;; or construct your command line manually
```

```clojure
(use 'kwnew.json)
(bench 50)
```

Builds a JSON map with 1000 random string keys and integers for values.
Each timing you see represents parsing that JSON string 100 times.

Times per round after warmup:
- 1.6.0 = ~54 ms
- 1.7.0-alpha1 = ~29 ms

## License

Copyright © 2014 Alex Miller

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
