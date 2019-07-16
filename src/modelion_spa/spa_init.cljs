(ns modelion-spa.spa-init
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]

   [modelion-spa.graphed-integration :as graphed-integration]
   [modelion-spa.ui-components.desktop :refer [Desktop]]
   [modelion-spa.events]
   [modelion-spa.subscriptions]))




(defn install-roboto-css []
  (let [head (.-head js/document)
        link (.createElement js/document "link")]
    (set! (.-type link) "text/css")
    (set! (.-rel link) "stylesheet")
    (set! (.-href link) "https://fonts.googleapis.com/css?family=Roboto:300,400,500")
    (.appendChild head link)))


(defn mount-app
  []
  (r/render [Desktop] (.getElementById js/document "app")))


(defn init-graphed
  []
  (graphed-integration/init))



(defonce init
  ((fn []
     (install-roboto-css)
     (init-graphed)
     (rf/dispatch [:modelion/init])
     (mount-app)
     :initialized)))
