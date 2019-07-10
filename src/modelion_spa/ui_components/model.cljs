(ns modelion-spa.ui-components.model
  (:require
   ["@material-ui/core" :as mui]
   [re-frame.core :as rf]
   [mui-commons.components :as muic]
   [mui-commons.nested-paperscraps :as nps]
   [modelion-spa.ui-components.commons :as c]))


(defn Model
  [model]
  [:div
   [:> mui/Paper
    (into
     [:> mui/MenuList]
     (map
      (fn [module]
        [:> mui/MenuItem
         {:on-click #(rf/dispatch [:modelion/activate-view
                                   {:view :module
                                    :entity-id (-> module :db/id)}])}
         (-> module :modeling/name)])
      (-> model :model/modules)))]])
