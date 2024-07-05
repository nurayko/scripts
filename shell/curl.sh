URL="https://example.com/"
token="adasdsa123xcasd+23dsa"
user=admin
password=admin123
filename="image.png"

# Headers
curl -H "Authorization: Token token=$token" $URL

# Basic Authentication
curl -u "$user:$password" $URL

# Upload file
curl -u "$user:$password" -T $filename $URL/$filename

# Download file
curl -O $filename $URL

# Post request
curl --request POST \
    --url $URL \
    --header 'Content-Type: application/x-www-form-urlencoded'
    --data "api_token=$token"

# Delete request
curl -X DELETE -u "$username:$password" $URL

# Pass file as body
curl --data "@$filename" $URL