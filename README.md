# FileValidator
Various file validator with a single facade for extensibility and cleaner code

## What's behind?
Most of the validators validates by:
- Read the source file as a byte array
- Convert to unsigned integer array if needed
- Compare the "magic-numbers"

## Usage
- Example usage included in junit test
- `FileValidator` is the main interface
- `validate()` is the function that does the validation

```Java
  FileValidator fv = FileValidatorDelegator.of(new File("someFile.pdf"));
```
and then you can call
```Java
  fv.validate();
```
and the interface will automatically find the right validator for the source file.
Note: This entire lib is not thread-safe

## Extension
You can easily create a new file validator type by extending `FileValidator` interface and name your concrete class using the following format:
`EXTENSION_NAME + FileValidator`
- Example: to validate a png, create a concrete class of name `PNGFileValidator`

## License
```
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
```
