import React, {Component} from 'react';
import {View, Text, StyleSheet} from 'react-native';
import data from '../db/PreviewJson.json';

class TabWriteScreen extends Component {
  render() {
    return (
      <View>
        <Text>{data.User_sc.date}</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({});

export default TabWriteScreen;
