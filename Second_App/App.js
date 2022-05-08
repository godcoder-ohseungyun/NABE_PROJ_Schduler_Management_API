/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import * as React from 'react';
import {StyleSheet, Image} from 'react-native';

import {NavigationContainer} from '@react-navigation/native';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import TabHomeScreen from './src/home_tab_example';
import TabUserScreen from './src/user_tab';
import TabWriteScreen from './src/write_tab';
import TabSearchScreen from './src/search_tab';
import TabExampleScreen from './src/example_tab';

const Tab = createBottomTabNavigator();

const TabBarIcon = (focused, name) => {
  let iconImagePath;

  if (name === 'Home') {
    iconImagePath = require('./src/assets/pics/home_icon.png');
  } else if (name === 'Search') {
    iconImagePath = require('./src/assets/pics/search_icon.png');
  } else if (name === 'User') {
    iconImagePath = require('./src/assets/pics/smile_icon.png');
  } else if (name === 'Write') {
    iconImagePath = require('./src/assets/pics/write_icon.png');
  } else if (name === 'Example') {
    iconImagePath = require('./src/assets/pics/write_icon.png');
  }

  return (
    <Image
      style={{
        width: focused ? 30 : 20,
        height: focused ? 30 : 20,
      }}
      source={iconImagePath}
    />
  );
};
const App: () => Node = () => {
  return (
    <NavigationContainer>
      <Tab.Navigator
        initialRouteName="Home"
        screenOptions={({route}) => ({
          tabBarLabel: route.name,
          tabBarIcon: ({focused}) => TabBarIcon(focused, route.name),
          tabBarActiveBackgroundColor: 'grey',
          tabBarActiveTintColor: 'black',
          tabBarInactiveTintColor: 'black',
          style: {
            backgroundColor: 'blue',
          },
          tabBarStyle: {
            height: 50,
          },
          tabBarItemStyle: {
            height: 50,
          },
        })}>
        <Tab.Screen name="Home" component={TabHomeScreen} />
        <Tab.Screen name="Search" component={TabSearchScreen} />
        <Tab.Screen name="User" component={TabUserScreen} />
        <Tab.Screen name="Write" component={TabWriteScreen} />
        <Tab.Screen name="Example" component={TabExampleScreen} />
      </Tab.Navigator>
    </NavigationContainer>
  );
};

const styles = StyleSheet.create({});

export default App;
