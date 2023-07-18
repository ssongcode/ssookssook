#include <DFRobot_DHT11.h> // 이 라이브러리를 사용하겠다. 
DFRobot_DHT11 DHT;
#define DHT11_PIN A0 // 아두이노 핀 10번으로 정의하겠다. (변경가능)

void setup(){
  Serial.begin(9600); //(컴퓨터와 통신을 115200속도로 하겠다.)
}

void loop(){
  DHT.read(DHT11_PIN);
  Serial.print("temp:");
  Serial.print(DHT.temperature); //온도를 출력하겠다.
  Serial.print("  humi:");
  Serial.println(DHT.humidity); //습도를 출력하겠다.
  delay(1000);
}
