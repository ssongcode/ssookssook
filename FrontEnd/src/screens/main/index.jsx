import { useState, useEffect } from "react";
import { ImageBackground, View, Image, TouchableOpacity } from "react-native";
import CookieRunRegular from "../../components/common/CookieRunRegular";
import ModalSetting from "../../components/modalsetting";
import ModalPlantSeed from "../../components/modalplantseed";
import ModalMap from "../../components/modalmap";
import ModalDictionary from "../../components/modaldictionary";
import styles from "./style";
import { useNavigation } from "@react-navigation/native";
import { connect } from "react-redux";
import customAxios from "../../utils/axios";

const MainScreen = (props) => {
  const navigation = useNavigation();
  const [isSettingModalVisible, setSettingModalVisible] = useState(false);
  const [isCharacterModalVisible, setCharacterModalVisible] = useState(false);
  const [isOpenMapModalVisible, setIsOpenMapModalVisible] = useState(false);
  const [isDictionaryModalVisible, setIsDictionaryModalVisible] =
    useState(false);
  const [temperature, setTemperature] = useState(0);
  const [moisture, setMoisture] = useState(0);
  const [humidity, setHumidity] = useState(0);

  const { potID } = props;

  const getUserData = () => {
    customAxios.get(`/sensor/${potID}`).then((res) => {
      console.log(res.data);

      const temperatureData = res.data.find(
        (sensor) => sensor.sensorType === "T"
      );
      const moistureData = res.data.find((sensor) => sensor.sensorType === "M");
      const humidityData = res.data.find((sensor) => sensor.sensorType === "H");

      if (temperatureData) {
        setTemperature(temperatureData.measurementValue);
      }

      if (moistureData) {
        setMoisture(moistureData.measurementValue);
      }

      if (humidityData) {
        setHumidity(humidityData.measurementValue);
      }
    });
  };

  useEffect(() => {
    // 컴포넌트가 마운트될 때 getUserData 함수 호출
    getUserData();

    // 30초마다 getUserData 함수 호출하는 interval 설정
    const interval = setInterval(() => {
      getUserData();
    }, 30000); // 30초를 밀리초로 변환

    // 컴포넌트가 언마운트될 때 interval 정리
    return () => {
      clearInterval(interval);
    };
  }, []);

  console.log("메인부분에서" + potID);

  const toggleSettingModal = () => {
    setSettingModalVisible(!isSettingModalVisible);
  };

  const toggleCharacterModal = () => {
    setCharacterModalVisible(!isCharacterModalVisible);
  };

  const toggleOpenMap = () => {
    setIsOpenMapModalVisible(!isOpenMapModalVisible);
  };

  const toggleOpenDictionary = () => {
    setIsDictionaryModalVisible(!isDictionaryModalVisible);
  };

  const handleSeedPlant = (nickname, option) => {
    // 씨앗 심기 관련 로직
    console.log("Planting seed with nickname: " + nickname);
    console.log("Selected option: " + option);
  };

  const handleWateringPlant = () => {
    customAxios.get(`/sensor/water/${potID}`).then(() => {
      console.log("성공");
    });
  };

  return (
    <View style={styles.container}>
      <ImageBackground
        source={require("../../assets/img/ProfileBackground.png")}
        style={styles.container}
      >
        <View style={styles.userInfoSection}>
          <View style={styles.SensorContainer}>
            <View style={styles.tmp}>
              <Image
                source={require("../../assets/img/tmpSensor.png")}
                resizeMode="contain"
                style={styles.sensorSize}
              />
              <CookieRunRegular style={styles.tmpText}>
                {temperature} C°
              </CookieRunRegular>
            </View>
            <View style={styles.tmp}>
              <Image
                source={require("../../assets/img/humiditySensor.png")}
                resizeMode="contain"
                style={styles.sensorSize}
              />
              <CookieRunRegular style={styles.tmpText}>
                {humidity} C°
              </CookieRunRegular>
            </View>
            <View style={styles.tmp}>
              <Image
                source={require("../../assets/img/moistureSensor.png")}
                resizeMode="contain"
                style={styles.sensorSize}
              />
              <CookieRunRegular style={styles.tmpText}>
                {moisture} C°
              </CookieRunRegular>
            </View>
            <TouchableOpacity onPress={toggleOpenMap}>
              <Image
                source={require("../../assets/img/map.png")}
                resizeMode="contain"
                style={styles.mapSize}
              />
            </TouchableOpacity>
          </View>
          <View style={styles.IconContainer}>
            <TouchableOpacity
              style={styles.iconBackground}
              onPress={() => navigation.navigate("Alarm")}
            >
              <Image
                source={require("../../assets/img/alarmIcon.png")}
                resizeMode="contain"
                style={styles.iconSize}
              />
            </TouchableOpacity>
            <TouchableOpacity style={styles.iconBackground}>
              <Image
                source={require("../../assets/img/boardIIcon.png")}
                resizeMode="contain"
                style={styles.iconSize}
              />
            </TouchableOpacity>
            <TouchableOpacity
              style={styles.iconBackground}
              onPress={toggleOpenDictionary}
            >
              <Image
                source={require("../../assets/img/dictionaryIcon.png")}
                resizeMode="contain"
                style={styles.iconSize}
              />
            </TouchableOpacity>
            <TouchableOpacity
              style={styles.iconBackground}
              onPress={toggleSettingModal}
            >
              <Image
                source={require("../../assets/img/settingIcon.png")}
                resizeMode="contain"
                style={styles.iconSize}
              />
            </TouchableOpacity>
          </View>
        </View>
        <View style={styles.nameTagSection}>
          <Image
            source={require("../../assets/img/nameTag.png")}
            resizeMode="contain"
            style={styles.nameTagSize}
          />
        </View>
        <TouchableOpacity
          style={styles.characterSection}
          onPress={toggleCharacterModal}
        >
          <Image
            source={require("../../assets/img/lettuce_3.gif")}
            resizeMode="contain"
            style={styles.characterSize}
          />
        </TouchableOpacity>
        <View style={styles.wateringCanSection}>
          <TouchableOpacity onPress={handleWateringPlant}>
            <Image
              source={require("../../assets/img/wateringCanIcon.png")}
              resizeMode="contain"
              style={styles.wateringCanSize}
            />
          </TouchableOpacity>
        </View>
      </ImageBackground>
      <ModalSetting
        isVisible={isSettingModalVisible}
        onClose={toggleSettingModal}
      />
      <ModalPlantSeed
        isVisible={isCharacterModalVisible}
        onClose={() => setCharacterModalVisible(false)}
        onSeedPlant={handleSeedPlant}
      />
      <ModalMap
        isVisible={isOpenMapModalVisible}
        onClose={() => setIsOpenMapModalVisible(false)}
        navigation={navigation}
      />
      <ModalDictionary
        isVisible={isDictionaryModalVisible}
        onClose={toggleOpenDictionary}
        navigation={navigation}
      />
    </View>
  );
};

const mapStateToProps = (state) => {
  return {
    potID: state.potID, // 'potID' should match the name you have in your Redux state
  };
};

export default connect(mapStateToProps)(MainScreen);
