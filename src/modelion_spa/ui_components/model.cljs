(ns modelion-spa.ui-components.model
  (:require
   ["@material-ui/core" :as mui]
   [re-frame.core :as rf]
   [mui-commons.components :as muic]
   [mui-commons.nested-paperscraps :as nps]
   [modelion-spa.ui-components.commons :as c]))


(defn Module
  [module]
  [c/Entity-Paper
   {:entity module
    :target-view :module}])


(defn Model
  [model]
  [:div
   [c/Entity-Paper
    {:entity model}
    [c/Papers-List Module (-> model :model/modules)]]])
   ;; [:hr]
   ;; [nps/Data model]])
