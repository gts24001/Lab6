# Lab6

echo "# Lab6" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/gts24001/Lab6.git
git push -u origin main



curl -X POST "http://localhost:8080/email-address-valid?email=test@example.com" -H "Content-Type: application/json"
# Valid Email

curl -X POST "http://localhost:8080/check-password-strength?password=MyStr0ng@Pass" -H "Content-Type: application/json"
# Very Strong

curl -X POST "http://localhost:8080/check-password-strength?password=Password123" -H "Content-Type: application/json"
# Expected output: "Medium"

curl -X POST "http://localhost:8080/say-hi-back" -H "Content-Type: application/json"
# Expected output: "Echo: Enjoy your day!"

cd /workspaces/Lab6/micro-services/demo && ./mvnw clean compile
./mvnw spring-boot:run 