(function() {
  define(['react', 'jquery', 'event_list_entry'], function(React, jQuery, EventListEntry) {
    var EventList;
    EventList = React.createClass({
      getInitialState: function() {
        return {
          hasLoaded: false,
          events: []
        };
      },
      componentDidMount: function() {
        var eventListApi;
        eventListApi = jsRoutes.controllers.Events.list();
        return eventListApi.ajax().done((function(_this) {
          return function(result) {
            if (_this.isMounted()) {
              return _this.setState({
                events: result.response,
                hasLoaded: true
              });
            }
          };
        })(this)).fail((function(_this) {
          return function(jqXHR, textStatus, errorThrown) {
            var resultCode;
            resultCode = jqXHR.status;
            if (_this.isMounted()) {
              return _this.setState({
                events: [],
                hasLoaded: true
              });
            }
          };
        })(this));
      },
      render: function() {
        var div, entries, eventListEntry;
        div = React.DOM.div;
        if (this.state.hasLoaded) {
          eventListEntry = React.createFactory(EventListEntry);
          entries = this.state.events.map(function(event) {
            return eventListEntry({
              key: event.id,
              event: event
            });
          });
          return div({
            key: 'el',
            className: 'eventEntries'
          }, entries);
        } else {
          return div({});
        }
      }
    });
    return EventList;
  });

}).call(this);

//# sourceMappingURL=event_list.js.map
