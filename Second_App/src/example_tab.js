// height이랑 width로 된거 flex로 나중에 바꿔줘야함!!!!!

import * as React from 'react';
import {useState, useEffect} from 'react';
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
  Calendar,
  CalendarList,
  Agenda,
  LocaleConfig,
} from 'react-native-calendars';
import ReactNativeZoomableView from '@dudigital/react-native-zoomable-view/src/ReactNativeZoomableView';
import WeekView from 'react-native-week-view';

const TabExampleScreen: () => Node = () => {
  const data = [
    {
      title: 'Lunch Appointment',
      subtitle: 'With Harry',
      start: new Date(2022, 5, 2, 13, 20),
      end: new Date(2022, 5, 2, 14, 20),
      color: '#390099',
    },
  ];

  LocaleConfig.locales['fr'] = {
    monthNames: [
      'January',
      'Fabruary',
      'March',
      'April',
      'May',
      'June',
      'July',
      'August',
      'September',
      'October',
      'November',
      'December',
    ],
    monthNamesShort: [
      'Jan.',
      'Feb.',
      'Mar',
      'Apr',
      'May',
      'Jun.',
      'Jul.',
      'Aug.',
      'Sep.',
      'Oct.',
      'Nov.',
      'Dec.',
    ],
    dayNames: [
      'Sunday',
      'Monday',
      'Tuesday',
      'Wednesday',
      'Thursday',
      'Friday',
      'Saturday',
    ],
    dayNamesShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
    today: 'today',
  };
  LocaleConfig.defaultLocale = 'fr';

  const vacation = {key: 'vacation', color: 'red', selectedDotColor: 'blue'};
  const massage = {key: 'massage', color: 'blue', selectedDotColor: 'blue'};
  const workout = {key: 'workout', color: 'green'};

  const MyTodayComponent = ({formattedDate, textStyle}) => (
    <Text style={[textStyle, {fontWeight: 'bold'}]}>{formattedDate}</Text>
  );

  const MyDayComponent = ({formattedDate, textStyle, isToday}) => (
    <Text style={textStyle}>Some text - {formattedDate}</Text>
  );

  const myEvents = [
    {
      id: 1,
      description: 'Event',
      startDate: new Date(2022, 5, 5, 12, 0),
      endDate: new Date(2022, 5, 5, 12, 30),
      color: 'blue',
      // ... more properties if needed,
    },
    // More events...
  ];

  const [zoomedIn, setZoomedIn] = useState(1);
  const startedZooming = props => {
    try {
      setZoomedIn(
        props.zoomLevel > 1
          ? zoomedIn == 3
            ? zoomedIn
            : zoomedIn + 1
          : zoomedIn == 1
          ? zoomedIn
          : zoomedIn - 1,
      );
    } catch (e) {
      console.error(e);
    }
  };

  // class TabHomeScreen extends Component {
  return (
    <View style={styles.container}>
      <View
        style={{flex: 1}}
        // 여기서부터 스케줄 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      >
        {/* 스케줄 여기에 추가 */}
        <ReactNativeZoomableView
          style={styles.calenderBox}
          maxZoom={1.00001}
          //   zoomEnabled={false}
          minZoom={0.99999}
          zoomStep={0.5}
          initialZoom={1}
          pinchToZoomInSensitivity={10}
          pinchToZoomOutSensitivity={10}
          onZoomEnd={(a, b, c) => {
            startedZooming(c);
            console.log(zoomedIn);
          }}>
          {zoomedIn == 1 ? (
            <Calendar
              style={{
                borderWidth: 1,
                borderColor: 'red',
                height: 440,
              }}
              // Specify theme properties to override specific styles for calendar parts. Default = {}
              theme={{
                backgroundColor: '#ffffff',
                calendarBackground: '#ffffff',
                textSectionTitleColor: '#b6c1cd',
                textSectionTitleDisabledColor: '#d9e1e8',
                selectedDayBackgroundColor: '#00adf5',
                selectedDayTextColor: '#ffffff',
                todayTextColor: '#00adf5',
                dayTextColor: '#2d4150',
                textDisabledColor: '#d9e1e8',
                dotColor: '#00adf5',
                selectedDotColor: '#ffffff',
                arrowColor: 'orange',
                disabledArrowColor: '#d9e1e8',
                monthTextColor: 'blue',
                indicatorColor: 'blue',
                textDayFontFamily: 'monospace',
                textMonthFontFamily: 'monospace',
                textDayHeaderFontFamily: 'monospace',
                textDayFontWeight: '300',
                textMonthFontWeight: 'bold',
                textDayHeaderFontWeight: '300',
                textDayFontSize: 16,
                textMonthFontSize: 16,
                textDayHeaderFontSize: 16,
              }}
              // Initially visible month. Default = now
              current={'2022-05-01'}
              // Minimum date that can be selected, dates before minDate will be grayed out. Default = undefined
              minDate={'2022-03-10'}
              // Maximum date that can be selected, dates after maxDate will be grayed out. Default = undefined
              maxDate={'2032-03-30'}
              // Handler which gets executed on day press. Default = undefined
              onDayPress={day => {
                console.log('selected day', day);
              }}
              // Handler which gets executed on day long press. Default = undefined
              onDayLongPress={day => {
                console.log('selected day', day);
              }}
              // Month format in calendar title. Formatting values: http://arshaw.com/xdate/#Formatting
              monthFormat={'yyyy MMMM'}
              // Handler which gets executed when visible month changes in calendar. Default = undefined
              onMonthChange={month => {
                console.log('month changed', month);
              }}
              // Hide month navigation arrows. Default = false
              // hideArrows={true}

              // Replace default arrows with custom ones (direction can be 'left' or 'right')
              // renderArrow={direction => <Arrow />}

              // Do not show days of other months in month page. Default = false
              // hideExtraDays={true}

              // If hideArrows = false and hideExtraDays = false do not switch month when tapping on greyed out
              // day from another month that is visible in calendar page. Default = false
              disableMonthChange={true}
              // If firstDay=1 week starts from Monday. Note that dayNames and dayNamesShort should still start from Sunday
              firstDay={1}
              // Hide day names. Default = false
              // hideDayNames={true}

              // Show week numbers to the left. Default = false
              // showWeekNumbers={true}

              // Handler which gets executed when press arrow icon left. It receive a callback can go back month
              onPressArrowLeft={subtractMonth => subtractMonth()}
              // Handler which gets executed when press arrow icon right. It receive a callback can go next month
              onPressArrowRight={addMonth => addMonth()}
              // Disable left arrow. Default = false
              // disableArrowLeft={true}

              // Disable right arrow. Default = false
              // disableArrowRight={true}

              // Disable all touch events for disabled days. can be override with disableTouchEvent in markedDates
              disableAllTouchEventsForDisabledDays={true}
              // Replace default month and year title with custom one. the function receive a date as parameter
              // renderHeader={date => {
              //   /*Return JSX*/
              // }}
              // Enable the option to swipe between months. Default = false
              enableSwipeMonths={true}
              // markingType={'multi-dot'}
              // markedDates={{
              //   '2022-05-25': {
              //     dots: [vacation, massage, workout],
              //     selected: true,
              //     selectedColor: 'red',
              //   },
              //   '2022-05-11': {dots: [massage, workout], disabled: true},
              // }}
              markingType={'period'}
              markedDates={{
                '2022-05-20': {textColor: 'green'},
                '2022-05-22': {startingDay: true, color: 'green'},
                '2022-05-23': {
                  selected: true,
                  endingDay: true,
                  color: 'green',
                  textColor: 'gray',
                },
                '2022-05-04': {
                  disabled: true,
                  startingDay: true,
                  color: 'red',
                  endingDay: true,
                },
              }}
            />
          ) : zoomedIn == 2 ? (
            <WeekView
              style={{backgroundColor: 'yellow'}}
              events={myEvents}
              selectedDate={Date(2022, 5, 5)}
              // numberOfDays를 통해 줌인 줌아웃해도 될 거 같음
              numberOfDays={5}
              DayHeaderComponent={MyDayComponent}
              showNowLine={true}
              pagingEnabled={true}

              // 좌우 고정
              //   fixedHorizontally={true}

              // 몇시부터 몇시까지 표시
              //   beginAgendaAt={8 * 60}
              //   endAgendaAt={22 * 60}
            />
          ) : (
            <WeekView
              style={{backgroundColor: 'yellow'}}
              events={myEvents}
              selectedDate={Date(2022, 5, 5)}
              // numberOfDays를 통해 줌인 줌아웃해도 될 거 같음
              numberOfDays={1}
              DayHeaderComponent={MyDayComponent}
              showNowLine={true}
              pagingEnabled={false}

              // 좌우 고정
              //   fixedHorizontally={true}

              // 몇시부터 몇시까지 표시
              //   beginAgendaAt={8 * 60}
              //   endAgendaAt={22 * 60}
            />
          )}
        </ReactNativeZoomableView>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'black',
    marginTop: 0,
  },
  secondContainer: {
    backgroundColor: 'grey',
  },
  messageBox: {
    height: 20,
    backgroundColor: 'orange',
  },
  resultBox: {
    backgroundColor: 'blue',
    fontSize: 20,
  },
  calenderBox: {
    backgroundColor: 'white',
  },
  button: {
    width: 50,
    height: 50,
  },
  header: {
    justifyContent: 'space-between',
    alignItems: 'center',
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
    marginRight: 0,
    fontSize: 18,
  },
  secondHeader: {
    color: 'black',
    marginVertical: 10,
    // marginLeft: 10,
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

export default TabExampleScreen;
