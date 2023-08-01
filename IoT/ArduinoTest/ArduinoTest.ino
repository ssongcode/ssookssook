#include <DFRobot_DHT11.h> // 온습도 센서 라이브러리 
DFRobot_DHT11 DHT; // 온습도 센서 객체 
#define PIN A0 // 아두이노 핀은 A0를 사용

void setup(){
  Serial.begin(9600);
}

void loop(){
  // 온습도 센서
  DHT.read(PIN);
  // Serial.print("temp:");
  // Serial.print(DHT.temperature); // 온도 출력
  // Serial.print("  humi:");
  // Serial.println(DHT.humidity); //습도 출력
  Serial.print(DHT.temperature);
  Serial.print(" ");
  Serial.print(DHT.humidity);
  Serial.print(" ");
  // 토양 수분 센서 (현재 -로 측정이 되는데 문제 확인 필요)
  int groundMoisture = analogRead(PIN);  
  groundMoisture = map(groundMoisture,550,0,0,100);  
  // Serial.print("Mositure : ");  
  // Serial.print(groundMoisture);  
  // Serial.println("%");
  Serial.print(groundMoisture);
  Serial.print(" ");
  // 수위 측정 센서 (물 감지시 300~500 사이, 물이 없다면 ~30 -> 범위는 100을 기준으로 두면 될듯)
  int waterTank = analogRead(PIN);
  // Serial.print("waterTank : ");
  // Serial.println(waterTank);
  Serial.print(waterTank);
  Serial.println();
  delay(30000);
}
