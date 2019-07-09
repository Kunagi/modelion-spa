(ns modelion-spa.spa-init
  (:require
   [reagent.core :as r]
   [modelion-spa.ui-components.desktop :refer [Desktop]]
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


(install-roboto-css)
(mount-app)
