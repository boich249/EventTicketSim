(function() {
  require.config({
    shim: {
      react: {
        exports: 'React'
      }
    },
    paths: {
      react: '../lib/react/react',
      jquery: '../lib/jquery/jquery'
    }
  });

  require(['react', 'event_list'], function(React, EventList) {
    var MasterView, div, masterView, rendered;
    div = React.DOM.div;
    MasterView = React.createClass({
      render: function() {
        var eventList;
        eventList = React.createFactory(EventList);
        return div({
          key: 'top'
        }, eventList({
          key: 'events'
        }, null));
      }
    });
    masterView = React.createElement(MasterView, null);
    return rendered = React.render(masterView, document.getElementById('mount'));
  });

}).call(this);

//# sourceMappingURL=index.js.map
