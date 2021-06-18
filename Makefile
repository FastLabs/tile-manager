.PHONY:hello
hello:
	echo "Hello World"
dev-styles:
	webpack --watch
repl:hello
	 clojure -Adev
