(ns modelion-spa.ui-components.desktop
  (:require
   [goog.object :as gobj]
   ["@material-ui/core" :as mui]
   ["@material-ui/core/styles" :refer [createMuiTheme withStyles]]
   ["@material-ui/core/colors" :as mui-colors]
   [re-frame.core :as rf]
   [mui-commons.components :as muic]
   [modelion-spa.ui-components.model :refer [Model]]))





(defn Navigator
  []
  (let [view   @(rf/subscribe [:modelion/navigator-view])
        entity @(rf/subscribe [:moelion/navigator-entity])]
    [:div
     [:h4
      "Modelion Navigator "
      view
      [Model entity]]]))


(def palette
  {:primary {:main (gobj/get (.-blueGrey mui-colors) 700)}
   :secondary {:main (gobj/get (.-green mui-colors) 700)}
   :text-color (gobj/get (.-red mui-colors) 700)

   :greyed "#aaa"})


(def theme {:palette palette
            :typography {:useNextVariants true}})

(def base-theme (createMuiTheme (clj->js theme)))

(defn Desktop
  []
  [:div
   {:style {:font-family "\"Roboto\", \"Helvetica\", \"Arial\", sans-serif"
            :color "#333"}}
   [:> mui/CssBaseline]
   [:> mui/MuiThemeProvider
    {:theme base-theme}
    [Navigator]]])
