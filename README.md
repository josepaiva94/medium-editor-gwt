# MediumEditor GWT

[MediumEditor](https://raw.github.com/yabwe/medium-editor) is a clone of [medium.com](https://medium.com) inline editor
toolbar. It has been written using vanilla JavaScript, no additional frameworks required. MediumEditor GWT is an 
adaptation of MediumEditor for Google Web Toolkit which also adds an emotion highlighter (actually, a multi-level
category highlighter).

[![screenshot](/docs/images/medium-editor.jpg)](/docs/images/medium-editor.jpg)

# Basic usage

### Demo

Run it

```bash
mvn gwt:devmode
```

### Package

Package a GWT lib

```bash
mvn gwt:package-lib
```

### Deploy

Deploy GWT

```bash
mvn gwt:package-lib deploy
```

## Development

To run the demo locally:

1. Clone this repo locally
2. Run `npm install` from your console at the root
3. Run `node index.js` from the root
4. Navigate to `http://localhost:8088/demo/index.html` to view the demo

MediumEditor development tasks are managed by Grunt. To install all the necessary packages, just invoke:

```bash
npm install
```

To run all the test and build the dist files for testing on demo pages, just invoke:
```bash
grunt
```

These are the other available grunt tasks:

* __js__: runs jslint and jasmine tests and creates minified and concatenated versions of the script;
* __css__: runs autoprefixer and csslint
* __test__: runs jasmine tests, jslint and csslint
* __watch__: watch for modifications on script/scss files
* __spec__: runs a task against a specified file

The source files are located inside the __src__ directory.  Be sure to make changes to these files and not files in the dist directory.

## Contributing

[Kill some bugs :)](https://github.com/josepaiva94/medium-editor-gwt/issues?q=is%3Aopen+is%3Aissue+label%3Abug)

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Test your changes to the best of your ability.
4. Update the documentation to reflect your changes if they add or changes current functionality.
5. Commit your changes (`git commit -am 'Added some feature'`) **without files from the _target_ directory**.
6. Push to the branch (`git push origin my-new-feature`)
7. Create new Pull Request

## License

[MIT License](LICENSE)
