(ns modelion-spa.modules.gesetze-index)

(def module-events
  [

   [:module--defined
    {:module
     {:db/id "a5ccab6c-2d71-4bde-93f5-3a231d8727e6"
      :module/ident :gesetze-index
      :modeling/name "Gesetze Index"
      :modeling/doc "Liste aller Gesetze. Filterbar."}}]


   [:resource--defined
    {:resource
     {:db/id "6566ed48-0e48-4cd7-9028-aec185e7e082"
      :resource/module "a5ccab6c-2d71-4bde-93f5-3a231d8727e6"
      :modeling/name "Index aller Gesetze"
      :modeling/doc "Liste aller Gesetze aus allen Kontexten."
      :resource/ident :index}}]


   [:ajax-resource-loader--defined
    {:resource "6566ed48-0e48-4cd7-9028-aec185e7e082"
     :ajax-resource-loader
     {:db/id "835299de-ce86-4a7c-9ac9-4293a7514dac"
      :modeling/name "index.edn"
      :ajax-resource-loader/module "a5ccab6c-2d71-4bde-93f5-3a231d8727e6"
      :ajax-resource-loader/url "/data/gesetze/index.edn"
      :ajax-resource-loader/load-on-startup true
      :ajax-resource-loader/cache-in-browser true}}]


   [:ui-component--defined
    {:ui-component
     {:db/id "c9aa30e1-e454-4bad-85da-05e7b518ca9e"
      :ui-component/module "a5ccab6c-2d71-4bde-93f5-3a231d8727e6"
      :modeling/name "Index aller Gesetze"
      :modeling/doc "Liste aller Gesetze. Kontext, Kürzel, Bezeichnung. Filterbar. Download-Status jedes Gesetzes."
      :ui-component/ident :index}}]


   [:ui-component--added--subscription
    {:ui-component "c9aa30e1-e454-4bad-85da-05e7b518ca9e"
     :subscription "6566ed48-0e48-4cd7-9028-aec185e7e082"}]])


   ;; [:page--defined
   ;;  {:page
   ;;   {:db/id "9002b220-dbde-48cf-a7ac-323984466f02"}}]


   ;; [:page--added--ui-component
   ;;  {:page "9002b220-dbde-48cf-a7ac-323984466f02"
   ;;   :ui-component "c9aa30e1-e454-4bad-85da-05e7b518ca9e"}]


   ;; :end])
