import { StatusBar } from "expo-status-bar";
import { StyleSheet, Button, View, Text } from "react-native";
import { StackNavigationProp } from '@react-navigation/stack';


type RootStackParamList = {
  Home: undefined, 
  SignUp: undefined 
};

type HomeScreenNavigationProp = StackNavigationProp<
 RootStackParamList,
 'Home'
>;

type Props = {
 navigation: HomeScreenNavigationProp;
};


function HomeScreen({ navigation }:Props) {
    return (
      <View>
        <Text>Home</Text>
        <Button
          title='Go to SignUp'
          onPress={() => navigation.navigate('SignUp')}
        />
        <StatusBar style='auto' />
      </View>
    );
  }

export default HomeScreen;


const styles = StyleSheet.create({
    container: {
        color: "red"
    }
});