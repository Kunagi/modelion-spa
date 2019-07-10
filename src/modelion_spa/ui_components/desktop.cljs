(ns modelion-spa.ui-components.desktop
  (:require
   [goog.object :as gobj]
   ["@material-ui/core" :as mui]
   ["@material-ui/core/styles" :refer [createMuiTheme withStyles]]
   ["@material-ui/core/colors" :as mui-colors]
   [re-frame.core :as rf]
   [mui-commons.components :as muic]
   [modelion-spa.ui-components.model :refer [Model]]
   [modelion-spa.ui-components.module :refer [Module]]))


(defn Navigator
  []
  (let [navigator  @(rf/subscribe [:modelion/navigator])
        view (:view navigator)
        entity (:entity navigator)]
    [:div
     ;[muic/Data entity]
     [:h4
      "Modelion Navigator " (str view)
      (case view
        :model [Model entity]
        :module [Module entity]
        [:div (str "Unsupported view: " view)])]]))


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
    [:div
     {:style {:padding "10px"}}
     [Navigator]]]])
