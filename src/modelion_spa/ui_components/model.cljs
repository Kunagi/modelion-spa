(ns modelion-spa.ui-components.model
  (:require
   [mui-commons.components :as muic]
   [mui-commons.nested-paperscraps :as nps]
   [modelion-spa.ui-components.commons :as c]))


(defn Module
  [module]
  [:div
   [:span
    {:style {:font-weight :normal
             :font-family :monospace
             :margin-right "1em"}}
    (str (-> module :module/ident))]

   (-> module :modeling/name)

   (when-let [doc (-> module :modeling/doc)]
     [:span
      {:style {:font-weight :normal
               :margin-left "1em"}}
      doc])])


(defn Model
  [model]
  [:div
   [nps/Sequence
    {:elements
     (map
      (fn [module] [Module module])
      (-> model :model/modules))}]])
