(ns modelion-spa.ui-components.module
  (:require
   ["@material-ui/core" :as mui]
   [re-frame.core :as rf]
   [mui-commons.components :as muic]
   [mui-commons.nested-paperscraps :as nps]
   [modelion-spa.ui-components.commons :as c]))


(defn Resource
  [resource]
  [c/Entity-Paper
   {:entity resource
    :target-view :resource}])


(defn ResourceLoader
  [resource-loader]
  [c/Entity-Paper
   {:entity resource-loader
    :target-view :resource-loader}])


(defn Module
  [module]
  [:div
   [c/Entity-Paper
    {:entity module}
    [:div

     [c/Labeled
      {:text "resources"}
      [c/Papers-List Resource (-> module :module/resources)]]

     [c/Labeled
      {:text "resource loaders"}
      [c/Papers-List ResourceLoader (-> module :module/resource-loaders)]]

     [c/Labeled
      {:text "data"}
      [muic/Data module]]]]])
    ;[c/Papers-List Module (-> model :model/modules)]]])
  ;; [:hr]
  ;; [nps/Data model]])
