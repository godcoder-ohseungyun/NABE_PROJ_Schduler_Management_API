/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import Voice from '@react-native-community/voice';
import {theme} from './colors';
import React, {useState, useEffect} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
  TouchableHighlight,
  Image,
  TouchableWithoutFeedback,
  TextInput,
  TouchableOpacity,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';

const App: () => Node = () => {
  const [pitch, setPitch] = useState('');
  const [error, setError] = useState('');
  const [end, setEnd] = useState('');
  const [started, setStarted] = useState('');
  const [results, setResults] = useState([]);
  const [partialResults, setPartialResults] = useState([]);

  const [working, setWorking] = useState(true);
  const [text, setText] = useState('');
  const [toDos, setToDos] = useState({});
  const travel = () => setWorking(false);
  const work = () => setWorking(true);
  const onChangeText = payload => setText(payload);
  const addToDo = () => {
    if (text === '') {
      return;
    }
    // save to do
    setText('');
  };

  const onSpeechStart = e => {
    console.log('onSpeechStart: ', e);
    setStarted('True');
  };
  const onSpeechEnd = e => {
    console.log('onSpeechEnd: ', e);
    setStarted(null);
    setEnd('True');
  };
  const onSpeechError = e => {
    console.log('onSpeechError: ', e);
    setError(JSON.stringify(e.error));
  };
  const onSpeechResults = e => {
    console.log('onSpeechResults: ', e);
    setResults(e.value);
  };
  const onSpeechPartialResults = e => {
    console.log('onSpeechPartialResults: ', e);
    setPartialResults(e.value);
  };
  const onSpeechVolumeChanged = e => {
    console.log('onSpeechVolumeChanged: ', e);
    setPitch(e.value);
  };

  useEffect(() => {
    Voice.onSpeechStart = onSpeechStart;
    Voice.onSpeechEnd = onSpeechEnd;
    Voice.onSpeechError = onSpeechError;
    Voice.onSpeechResults = onSpeechResults;
    Voice.onSpeechPartialResults = onSpeechPartialResults;
    Voice.onSpeechVolumeChanged = onSpeechVolumeChanged;

    return () => {
      Voice.destroy().then(Voice.removeAllListeners);
    };
  }, []);

  const startSpeechRecognizing = async () => {
    try {
      await Voice.start(
        'en-US',
        // , {EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS: 10000,}
      );
      setPitch('');
      setError('');
      setStarted('');
      setResults([]);
      setPartialResults([]);
      setEnd('');
    } catch (e) {
      console.error(e);
    }
  };
  const stopSpeechRecognizing = async () => {
    try {
      await Voice.stop();
      setStarted(null);
    } catch (e) {
      console.error(e);
    }
  };

  return (
    <View style={styles.container}>
      <StatusBar style="auto" />
      <View style={styles.header}>
        <TouchableWithoutFeedback onPress={work}>
          <Text
            style={{
              ...styles.btnText,
              color: working ? 'white' : theme.grey,
              flex: 1,
            }}>
            NAV=I
          </Text>
        </TouchableWithoutFeedback>

        <TextInput
          onSubmitEditing={addToDo}
          onChangeText={onChangeText}
          value={text}
          placeholder={working ? 'Add a To Do' : 'Where do you Want to go?'}
          style={{...styles.input, flex: 2}}
        />
        <TouchableOpacity onPress={travel}>
          <Text
            style={{
              ...styles.btnText,
              color: !working ? 'white' : theme.grey,
              // flex: 1,
            }}>
            Sound
          </Text>
        </TouchableOpacity>
      </View>
      <View>
        <Text style={styles.secondHeader}>
          새로운 내일을 만날 준비가 됐나요?
        </Text>
      </View>
      <View style={styles.keywordSection}>
        <TouchableOpacity>
          <Text style={styles.keywordText}>키워드</Text>
        </TouchableOpacity>
        <TouchableOpacity>
          <Text style={styles.keywordText}>모의 면접</Text>
        </TouchableOpacity>
        <TouchableOpacity>
          <Text style={styles.keywordText}>토익 스터디</Text>
        </TouchableOpacity>
      </View>
      <View style={styles.container}>
        <ScrollView style={styles.messageBox}>
          {partialResults.map((result, index) => {
            return (
              <Text key={`partial-result-${index}`} style={styles.resultBox}>
                {result}
              </Text>
            );
          })}
        </ScrollView>
        <View style={styles.secondContainer}>
          {!started ? (
            <TouchableHighlight
              onPress={startSpeechRecognizing}
              style={{marginVertical: 1, width: 50}}>
              <Image
                style={styles.button}
                source={{
                  uri: 'https://png.pngtree.com/png-vector/20190329/ourlarge/pngtree-vector-microphone-icon-png-image_889382.jpg',
                }}
              />
            </TouchableHighlight>
          ) : (
            <TouchableHighlight
              onPress={stopSpeechRecognizing}
              style={{marginVertical: 1, width: 50}}>
              <Image
                style={styles.button}
                source={{
                  uri: 'https://preview.redd.it/axorctfsk4v01.jpg?auto=webp&s=b9f5f8c1a353bd10aa7f3fa61e24b756ff042a7b',
                }}
              />
            </TouchableHighlight>
          )}
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'black',
    marginTop: 40,
  },
  secondContainer: {
    flex: 1,
    backgroundColor: 'grey',
  },
  messageBox: {
    flex: 1,
    backgroundColor: 'white',
  },
  resultBox: {
    backgroundColor: 'blue',
    fontSize: 20,
  },
  button: {
    width: 50,
    height: 50,
  },
  header: {
    justifyContent: 'space-between',
    flexDirection: 'row',
    marginTop: 20,
    paddingHorizontal: 20,
    backgroundColor: 'red',
  },
  btnText: {
    fontSize: 15,
    fontWeight: '600',
  },
  input: {
    backgroundColor: 'white',
    paddingVertical: 10,
    paddingHorizontal: 10,
    marginTop: -10,
    marginRight: 10,
    fontSize: 18,
  },
  secondHeader: {
    color: 'white',
    marginVertical: 35,
    marginLeft: 10,
    fontSize: 15,
    backgroundColor: 'blue',
  },
  keywordSection: {
    flexDirection: 'row',
  },
  keywordText: {
    alignItems: 'center',
    justifyContent: 'center',
    borderWidth: 1,
    borderRadius: 5,
    color: 'white',
    width: 100,
    backgroundColor: 'green',
  },
});

export default App;
