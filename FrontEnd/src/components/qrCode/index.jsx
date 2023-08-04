import React, { useState, useEffect } from "react";
import { View, Text, StyleSheet, TouchableOpacity } from "react-native";
import { BarCodeScanner } from "expo-barcode-scanner";
import Icon from "react-native-vector-icons/MaterialIcons";

const QRCodeScanner = ({ onScannedData, onCloseQRCodeScanner }) => {
  const [hasPermission, setHasPermission] = useState(null);
  const [scannedData, setScannedData] = useState(null);

  useEffect(() => {
    (async () => {
      const { status } = await BarCodeScanner.requestPermissionsAsync();
      setHasPermission(status === "granted");
    })();
  }, []);

  const handleBarCodeScanned = ({ type, data }) => {
    setScannedData(data);
    // Pass the scanned data back to PotScreen using the onScannedData prop
    onScannedData(data);
  };

  if (hasPermission === null) {
    return <Text>Requesting camera permission...</Text>;
  }
  if (hasPermission === false) {
    return <Text>Unable to access camera</Text>;
  }

  return (
    <View style={styles.container}>
      <BarCodeScanner
        style={{
          width: "100%",
          height: "100%",
        }}
        onBarCodeScanned={handleBarCodeScanned}
      >
        {/* Button to close QRCodeScanner */}
        <TouchableOpacity
          style={{ position: "absolute", top: 55, left: 30 }}
          onPress={onCloseQRCodeScanner}
        >
          <Icon name="arrow-back-ios" size={28} color="#000" />
        </TouchableOpacity>
      </BarCodeScanner>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    height: "100%",
  },
});

export default QRCodeScanner;
