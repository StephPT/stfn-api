curl --header "Content-Type: application/json" --request POST --data '{
  "name": "TestField",
  "label": "Test Field",
  "type": "text",
  "placeholder": "I'm a placeholder!",
  "prefix": "",
  "suffix": " ",
  "italic": "1",
  "required": false
}' http://localhost:8080/request/fields/save