import { useEffect, useState } from 'react';
import { StyleSheet, Text, TextInput, View, Image, Button, NativeSyntheticEvent, TextInputFocusEventData, findNodeHandle, TextInputChangeEventData, TextInputSubmitEditingEventData } from 'react-native';
 

function SignUpScreen() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [pw, setPW] = useState("");
  const [checkpw, setCheckPW] = useState("");

  return(
      <View style={styles.container}>

          <View style={styles.head}>
            <Image source={require('../assets/headerFW.png')} style={styles.image}/>
            <Text style={styles.title}>회원 가입</Text>
            <Text style={styles.subtitle}>지금 FLIP WALLET에 가입해 나의 소비를 관리해 봐요!</Text>
          </View>

          <View style={styles.list}>
            <View style={styles.question}>
              <Text style={styles.text}>이름</Text>
              <TextInput 
                onSubmitEditing={()=>setName(name)}
                style={styles.input} 
                returnKeyType='done'
                placeholder='이름 입력' 
              />
            </View>
            
            <View style={styles.question}>
              <Text style={styles.text}>이메일</Text>
              <View style={styles.email}>
                <TextInput 
                  value={email} 
                  style={styles.input} 
                  keyboardType='email-address' 
                  returnKeyType='done'
                  placeholder='이메일 입력'
                />
                <Button title="이메일 중복 확인"/>
              </View>
            </View>

            <View style={styles.question}>
              <Text style={styles.text}>전화번호</Text>
              <TextInput
               value={phone} 
               style={styles.input} 
               keyboardType='number-pad' 
               returnKeyType='done'
               placeholder='전화번호 입력'
              />
            </View>

            <View style={styles.question}>
              <Text style={styles.text}>비밀번호 설정</Text>
              <TextInput 
                value={pw} 
                style={styles.input} 
                secureTextEntry
                returnKeyType='done' 
                placeholder='비밀번호 입력'
              />
            </View>

            <View style={styles.question}>
              <Text style={styles.text}>비밀번호 확인</Text>
              <TextInput 
                value={checkpw} 
                style={styles.input} 
                secureTextEntry 
                returnKeyType='done'
                placeholder='비밀번호 확인'
              />
            </View>

          </View>

          <View style={styles.apply}>
            <Button title="회원가입 하기"></Button>
          </View>

        </View>
    );
}

export default SignUpScreen;

const styles = StyleSheet.create({
    container: {
      flex:1,
      margin: 10
    },
  
    head: {
      flex: 2,
      justifyContent: "flex-end",
    },
  
    list: {
      flex: 7,
      marginTop: 10,
    },
    
    apply: {
      flex: 1,
    },
  
    image : {
      justifyContent: "center",
      height: 50,
      resizeMode: "contain"
    },
  
    question: {
      flex:1,      
    },
  
    input: {
      height: 50,
      borderWidth: 1,
      borderRadius: 5,
      borderStyle: "solid",
      borderColor: "grey",
      alignContent: "center",
      marginTop: 5,
      backgroundColor: "white",
      paddingHorizontal: 10
    },
    
    email: {
      flex: 1,
    },
  
    title:{
      fontSize: 27,
      marginTop: 10
    },
    
    subtitle:{
      marginTop: 8,
      fontSize: 16,
      color: "grey"
    },
    
    text : {
      fontSize: 15,
      fontWeight: "bold",
      color: "grey"
    }
  });
  